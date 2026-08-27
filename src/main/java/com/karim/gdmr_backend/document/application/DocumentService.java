package com.karim.gdmr_backend.document.application;

import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.auth.domain.port.in.ListUsersUseCase;
import com.karim.gdmr_backend.document.domain.exception.MedicalDocumentNotFoundException;
import com.karim.gdmr_backend.document.domain.exception.UnauthorizedDocumentAccessException;
import com.karim.gdmr_backend.document.domain.exception.UnauthorizedDocumentTypeException;
import com.karim.gdmr_backend.document.domain.exception.UnsupportedFileTypeException;
import com.karim.gdmr_backend.document.domain.model.DocumentType;
import com.karim.gdmr_backend.document.domain.model.MedicalDocument;
import com.karim.gdmr_backend.document.domain.port.in.DeleteDocumentUseCase;
import com.karim.gdmr_backend.document.domain.port.in.DownloadDocumentUseCase;
import com.karim.gdmr_backend.document.domain.port.in.ListEmployeeDocumentsUseCase;
import com.karim.gdmr_backend.document.domain.port.in.UploadDocumentUseCase;
import com.karim.gdmr_backend.document.domain.port.out.MedicalDocumentRepositoryPort;
import com.karim.gdmr_backend.employee.domain.port.in.GetEmployeeByIdUseCase;
import com.karim.gdmr_backend.notification.domain.model.NotificationType;
import com.karim.gdmr_backend.notification.domain.port.in.SendNotificationUseCase;
import com.karim.gdmr_backend.shared.storage.FileStoragePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DocumentService implements UploadDocumentUseCase, ListEmployeeDocumentsUseCase, DownloadDocumentUseCase, DeleteDocumentUseCase {

    private static final Set<DocumentType> DOCTOR_ONLY_TYPES = Set.of(
            DocumentType.EXAMINATION_REPORT, DocumentType.MEDICAL_CERTIFICATE, DocumentType.PRESCRIPTION);

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "application/pdf", "image/jpeg", "image/png");

    private final MedicalDocumentRepositoryPort documentRepository;
    private final FileStoragePort fileStorage;
    private final SendNotificationUseCase sendNotificationUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
    private final ListUsersUseCase listUsersUseCase;

    public DocumentService(MedicalDocumentRepositoryPort documentRepository, FileStoragePort fileStorage,
                            SendNotificationUseCase sendNotificationUseCase,
                            GetEmployeeByIdUseCase getEmployeeByIdUseCase,
                            ListUsersUseCase listUsersUseCase) {
        this.documentRepository = documentRepository;
        this.fileStorage = fileStorage;
        this.sendNotificationUseCase = sendNotificationUseCase;
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
        this.listUsersUseCase = listUsersUseCase;
    }

    @Override
    public MedicalDocument upload(UploadDocumentCommand command) {
        validateRoleCanUploadType(command.documentType(), command.uploaderRole());
        validateFileType(command.file().getContentType());

        int version = 1;
        if (command.previousVersionId() != null) {
            MedicalDocument previous = documentRepository.findById(command.previousVersionId())
                    .orElseThrow(() -> new MedicalDocumentNotFoundException(command.previousVersionId()));
            version = previous.getVersion() + 1;
        }

        String storageKey = fileStorage.store(command.file(), "documents/" + command.employeeId());

        MedicalDocument document = documentRepository.save(MedicalDocument.createNew(
                command.employeeId(), command.visitId(), command.documentType(),
                command.file().getOriginalFilename(), storageKey,
                command.uploadedByUserId(), version, command.previousVersionId()));

        notifyOfUpload(document, command.uploaderRole());

        return document;
    }

    @Override
    public List<MedicalDocument> listForEmployee(Long employeeId) {
        return documentRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public void delete(DeleteDocumentCommand command) {
        if (!command.requestingRole().equals("DOCTOR")) {
            throw new UnauthorizedDocumentAccessException(command.documentId());
        }
        MedicalDocument document = documentRepository.findById(command.documentId())
                .orElseThrow(() -> new MedicalDocumentNotFoundException(command.documentId()));

        fileStorage.delete(document.getStorageKey());
        documentRepository.deleteById(command.documentId());
    }

    @Override
    public DownloadResult download(DownloadCommand command) {
        MedicalDocument document = documentRepository.findById(command.documentId())
                .orElseThrow(() -> new MedicalDocumentNotFoundException(command.documentId()));

        boolean isOwner = command.requestingRole().equals("EMPLOYEE")
                && document.getEmployeeId().equals(command.requestingEmployeeId());
        boolean isDoctor = command.requestingRole().equals("DOCTOR");

        if (!isOwner && !isDoctor) {
            throw new UnauthorizedDocumentAccessException(command.documentId());
        }

        byte[] content = fileStorage.load(document.getStorageKey());
        return new DownloadResult(content, document.getOriginalFilename());
    }

    private void validateRoleCanUploadType(DocumentType type, String role) {
        boolean isDoctorType = DOCTOR_ONLY_TYPES.contains(type);

        if (isDoctorType && !role.equals("DOCTOR")) {
            throw new UnauthorizedDocumentTypeException(type, role);
        }
        // HR may also attach a fitness/healing certificate on the employee's behalf
        // when scheduling a return-to-work visit (e.g. after illness or maternity leave).
        if (!isDoctorType && !role.equals("EMPLOYEE") && !role.equals("HR")) {
            throw new UnauthorizedDocumentTypeException(type, role);
        }
    }

    private void validateFileType(String contentType) {
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new UnsupportedFileTypeException(contentType);
        }
    }

    private void notifyOfUpload(MedicalDocument document, String uploaderRole) {
        String title = "New document added";
        String message = document.getDocumentType() + " uploaded: " + document.getOriginalFilename();

        if (uploaderRole.equals("DOCTOR") || uploaderRole.equals("HR")) {
            // Tell the employee it's their own file that changed.
            Long employeeUserId = getEmployeeByIdUseCase.getEmployeeById(document.getEmployeeId()).getUserId();
            sendNotificationUseCase.send(new SendNotificationUseCase.SendNotificationCommand(
                    employeeUserId, NotificationType.DOCUMENT_UPLOADED, title, message, document.getVisitId()));
        } else {
            // Employee-uploaded certificates aren't visible to HR in-app yet, so at
            // least let them know one came in.
            var hrUsers = listUsersUseCase.listUsers(
                    new ListUsersUseCase.ListUsersQuery(null, Role.HR, true, 0, 200));
            for (User hr : hrUsers.content()) {
                sendNotificationUseCase.send(new SendNotificationUseCase.SendNotificationCommand(
                        hr.getId(), NotificationType.DOCUMENT_UPLOADED, title, message, document.getVisitId()));
            }
        }
    }
}
