package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProfilePictureUseCase {

    User updateProfilePicture(UpdateProfilePictureCommand command);

    record UpdateProfilePictureCommand(Long userId, MultipartFile file) {}
}
