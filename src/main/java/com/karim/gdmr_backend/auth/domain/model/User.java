package com.karim.gdmr_backend.auth.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

    private final Long id;
    private final String email;
    private final String hashedPassword;
    private final String firstName;
    private final String lastName;
    private final Role role;
    private final boolean active;
    private final String cin;
    private final String profilePictureKey;
    private final String profilePictureContentType;

    public static User createNew(
            String email,
            String hashedPassword,
            String firstName,
            String lastName,
            Role role,
            boolean active
    ) {
        return new User(
                null,
                email,
                hashedPassword,
                firstName,
                lastName,
                role,
                active,
                null,
                null,
                null
                );
    }

    public User withActive(boolean active) {
        return new User(id, email, hashedPassword, firstName, lastName, role, active, cin,
                profilePictureKey, profilePictureContentType);
    }

    //Wither method:
    //It takes the new profile values (firstName, lastName, role, cin).
    //It creates and returns a brand new User object.
    //It copies over the fields that shouldn't change (this.id, this.email, this.hashedPassword, this.active) and replaces only the fields you want to update.
    public User updateProfile(String firstName, String lastName, Role role, String cin) {
        return new User(this.id, this.email, this.hashedPassword, firstName, lastName, role, this.active, cin,
                this.profilePictureKey, this.profilePictureContentType);
    }

    public User withProfilePicture(String profilePictureKey, String profilePictureContentType) {
        return new User(id, email, hashedPassword, firstName, lastName, role, active, cin,
                profilePictureKey, profilePictureContentType);
    }

    public boolean hasProfilePicture() {
        return profilePictureKey != null;
    }
}
