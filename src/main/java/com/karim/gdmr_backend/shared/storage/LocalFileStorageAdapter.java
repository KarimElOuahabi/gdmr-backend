package com.karim.gdmr_backend.shared.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class LocalFileStorageAdapter implements FileStoragePort {

    private final Path basePath;

    public LocalFileStorageAdapter(@Value("${app.file-storage.base-path}") String basePath) {
        this.basePath = Paths.get(basePath);
    }

    @Override
    public String store(MultipartFile file, String subDirectory) {
        try {
            Path targetDir = basePath.resolve(subDirectory);
            Files.createDirectories(targetDir);

            String safeFilename = UUID.randomUUID() + "_" + sanitize(file.getOriginalFilename());
            Path targetFile = targetDir.resolve(safeFilename);

            file.transferTo(targetFile);

            return subDirectory + "/" + safeFilename;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to store file", e);
        }
    }

    @Override
    public byte[] load(String storageKey) {
        try {
            return Files.readAllBytes(basePath.resolve(storageKey));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load file: " + storageKey, e);
        }
    }

    @Override
    public void delete(String storageKey) {
        try {
            Files.deleteIfExists(basePath.resolve(storageKey));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to delete file: " + storageKey, e);
        }
    }

    private String sanitize(String filename) {
        if (filename == null) return "file";
        return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}