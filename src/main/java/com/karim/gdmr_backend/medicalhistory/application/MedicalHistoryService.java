package com.karim.gdmr_backend.medicalhistory.application;

import com.karim.gdmr_backend.medicalhistory.domain.model.MedicalHistoryEntry;
import com.karim.gdmr_backend.medicalhistory.domain.port.in.AddMedicalHistoryEntryUseCase;
import com.karim.gdmr_backend.medicalhistory.domain.port.in.ListMedicalHistoryUseCase;
import com.karim.gdmr_backend.medicalhistory.domain.port.out.MedicalHistoryRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicalHistoryService implements AddMedicalHistoryEntryUseCase, ListMedicalHistoryUseCase {

    private final MedicalHistoryRepositoryPort repository;

    public MedicalHistoryService(MedicalHistoryRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public MedicalHistoryEntry addEntry(AddMedicalHistoryEntryCommand command) {
        MedicalHistoryEntry entry = MedicalHistoryEntry.createNew(
                command.employeeId(), command.doctorId(), command.visitId(),
                command.category(), command.description());
        return repository.save(entry);
    }

    @Override
    public List<MedicalHistoryEntry> listForEmployee(Long employeeId) {
        return repository.findAllByEmployeeId(employeeId);
    }
}
