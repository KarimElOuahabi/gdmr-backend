package com.karim.gdmr_backend.auth.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.User;

public interface DeleteProfilePictureUseCase {

    User deleteProfilePicture(Long userId);
}
