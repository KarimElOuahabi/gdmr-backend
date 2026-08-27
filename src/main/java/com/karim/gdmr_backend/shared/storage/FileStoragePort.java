package com.karim.gdmr_backend.shared.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStoragePort {
    String store(MultipartFile file, String subDirectory);
    byte[] load(String storageKey);
    void delete(String storageKey);
}