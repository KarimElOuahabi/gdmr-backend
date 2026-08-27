package com.karim.gdmr_backend.auth.application;

import com.karim.gdmr_backend.auth.domain.exception.UserNotFoundException;
import com.karim.gdmr_backend.auth.domain.model.AuthTokens;
import com.karim.gdmr_backend.auth.domain.model.RefreshToken;
import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.auth.domain.port.in.LoginUseCase;
import com.karim.gdmr_backend.auth.domain.port.in.LogoutUseCase;
import com.karim.gdmr_backend.auth.domain.port.in.RefreshAccessTokenUseCase;
import com.karim.gdmr_backend.auth.domain.port.in.RegisterUserUseCase;

import com.karim.gdmr_backend.auth.domain.port.out.*;
import com.karim.gdmr_backend.auth.domain.port.in.*;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthService implements RegisterUserUseCase, LoginUseCase, RefreshAccessTokenUseCase, LogoutUseCase, GetCurrentUserUseCase {

    private static final long REFRESH_TOKEN_TTL_DAYS = 7;

    private final UserRepositoryPort userRepository;
    private final PasswordHasherPort passwordHasher;
    private final TokenGeneratorPort tokenGenerator;
    private final RefreshTokenPort refreshToken;
    private final RefreshTokenRepositoryPort refreshTokenRepository;

    public AuthService(UserRepositoryPort userRepository,
                       PasswordHasherPort passwordHasher,
                       TokenGeneratorPort tokenGenerator,
                       RefreshTokenPort refreshTokenPort,
                       RefreshTokenRepositoryPort refreshTokenRepository) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenGenerator = tokenGenerator;
        this.refreshToken = refreshTokenPort;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public User register(String email, String rawPassword, String firstName, String lastName, Role role) {
        userRepository.findByEmail(email).ifPresent(u -> {
            throw new IllegalArgumentException("This email already registered");
        });

        String hashedPassword = passwordHasher.hash(rawPassword);
        User newUser = User.createNew(email, hashedPassword, firstName, lastName, role, true);
        return userRepository.save(newUser);
    }

    @Override
    public AuthTokens login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Credentials"));

        if(!passwordHasher.matches(rawPassword, user.getHashedPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (!user.isActive()) {
            throw new IllegalStateException("Account is deactivated");
        }

        return issueTokens(user);
    }

    @Override
    public AuthTokens refresh(String rawRefreshToken) {
        String hash = refreshToken.hashToken(rawRefreshToken);

        RefreshToken existing = refreshTokenRepository.findValidByHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired refresh token"));

        User user = userRepository.findById(existing.getUserId())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (!user.isActive()) {
            throw new IllegalStateException("Account is deactivated");
        }

        return issueTokens(user);
    }

    @Override
    public void logout(String rawRefreshToken) {
          String hash = refreshToken.hashToken(rawRefreshToken);
          refreshTokenRepository.revokeByHash(hash);
    }

    @Override
    public User getCurrentUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private AuthTokens issueTokens(User user) {
        String accessToken = tokenGenerator.generateToken(user);

        String rawRefreshToken = refreshToken.generateRawToken();
        String hash = refreshToken.hashToken(rawRefreshToken);
        Instant expiresAt = Instant.now().plus(REFRESH_TOKEN_TTL_DAYS, ChronoUnit.DAYS);

        RefreshToken refreshToken = RefreshToken.createNew(user.getId(), hash, expiresAt);
        refreshTokenRepository.save(refreshToken);

        return new AuthTokens(accessToken, rawRefreshToken);
    }

}
