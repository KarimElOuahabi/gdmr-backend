package com.karim.gdmr_backend.document.domain.port.in;

public interface DownloadDocumentUseCase {

    DownloadResult download(DownloadCommand command);

    record DownloadCommand(Long documentId, Long requestingUserId, String requestingRole, Long requestingEmployeeId) {}
    record DownloadResult(byte[] content, String filename) {}
}