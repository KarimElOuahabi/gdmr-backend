package com.karim.gdmr_backend.auth.domain.port.in;

public interface GetProfilePictureUseCase {

    ProfilePicture getProfilePicture(Long userId);

    record ProfilePicture(byte[] data, String contentType) {}
}
