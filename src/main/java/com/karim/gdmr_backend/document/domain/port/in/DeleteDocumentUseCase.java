package com.karim.gdmr_backend.document.domain.port.in;

public interface DeleteDocumentUseCase {

    void delete(DeleteDocumentCommand command);

    record DeleteDocumentCommand(Long documentId, String requestingRole) {}
}
