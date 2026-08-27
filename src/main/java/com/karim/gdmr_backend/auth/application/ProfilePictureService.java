package com.karim.gdmr_backend.auth.application;

import com.karim.gdmr_backend.auth.domain.exception.InvalidProfilePictureException;
import com.karim.gdmr_backend.auth.domain.exception.UserNotFoundException;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.auth.domain.port.in.DeleteProfilePictureUseCase;
import com.karim.gdmr_backend.auth.domain.port.in.GetProfilePictureUseCase;
import com.karim.gdmr_backend.auth.domain.port.in.UpdateProfilePictureUseCase;
import com.karim.gdmr_backend.auth.domain.port.out.UserRepositoryPort;
import com.karim.gdmr_backend.shared.storage.FileStoragePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

// Every role can attach a profile picture to their own account (auth.User is
// the one place shared by all four roles). Validation deliberately mimics the
// rules for an official/administrative ID photo — a real, front-facing,
// portrait-ish headshot — since we have no face-detection service available:
// square-ish framing and a sane minimum resolution are the closest proxy we
// can enforce automatically without one.
@Service
@Transactional
public class ProfilePictureService implements
        UpdateProfilePictureUseCase, GetProfilePictureUseCase, DeleteProfilePictureUseCase {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("image/jpeg", "image/png");
    private static final long MAX_FILE_SIZE_BYTES = 5L * 1024 * 1024; // 5 MB
    private static final int MIN_DIMENSION_PX = 300;
    private static final double MIN_ASPECT_RATIO = 0.75; // portrait, e.g. 3:4
    private static final double MAX_ASPECT_RATIO = 1.15; // near-square

    private static final String STORAGE_SUBDIRECTORY = "profile-pictures";

    private final UserRepositoryPort userRepository;
    private final FileStoragePort fileStorage;

    public ProfilePictureService(UserRepositoryPort userRepository, FileStoragePort fileStorage) {
        this.userRepository = userRepository;
        this.fileStorage = fileStorage;
    }

    @Override
    public User updateProfilePicture(UpdateProfilePictureCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        MultipartFile file = command.file();
        validate(file);

        String previousKey = user.getProfilePictureKey();
        String storageKey = fileStorage.store(file, STORAGE_SUBDIRECTORY);

        User updated = userRepository.save(user.withProfilePicture(storageKey, file.getContentType()));

        if (previousKey != null) {
            fileStorage.delete(previousKey);
        }

        return updated;
    }

    @Override
    @Transactional(readOnly = true)
    public ProfilePicture getProfilePicture(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (!user.hasProfilePicture()) {
            throw new UserNotFoundException(userId);
        }

        byte[] data = fileStorage.load(user.getProfilePictureKey());
        return new ProfilePicture(data, user.getProfilePictureContentType());
    }

    @Override
    public User deleteProfilePicture(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.hasProfilePicture()) {
            fileStorage.delete(user.getProfilePictureKey());
        }

        return userRepository.save(user.withProfilePicture(null, null));
    }

    private void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new InvalidProfilePictureException("Please choose a picture to upload.");
        }

        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new InvalidProfilePictureException("The picture must be smaller than 5 MB.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType)) {
            throw new InvalidProfilePictureException(
                    "Only JPG and PNG pictures are accepted.");
        }

        BufferedImage image;
        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            throw new InvalidProfilePictureException(
                    "This file doesn't look like a valid picture. Please upload a real photo.");
        }

        if (image == null) {
            throw new InvalidProfilePictureException(
                    "This file doesn't look like a valid picture. Please upload a real photo.");
        }

        int width = image.getWidth();
        int height = image.getHeight();

        if (width < MIN_DIMENSION_PX || height < MIN_DIMENSION_PX) {
            throw new InvalidProfilePictureException(
                    "The picture is too small — please use a clear photo at least "
                            + MIN_DIMENSION_PX + "x" + MIN_DIMENSION_PX + "px.");
        }

        double aspectRatio = (double) width / height;
        if (aspectRatio < MIN_ASPECT_RATIO || aspectRatio > MAX_ASPECT_RATIO) {
            throw new InvalidProfilePictureException(
                    "Please upload an ID-photo-style picture: a front-facing portrait, "
                            + "framed square or slightly taller than wide — not a wide/landscape image.");
        }
    }
}
