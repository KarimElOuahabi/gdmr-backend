package com.karim.gdmr_backend.auth.adapter.out.security;

import com.karim.gdmr_backend.auth.domain.port.out.TemporaryPasswordGeneratorPort;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SecureTemporaryPasswordAdapter implements TemporaryPasswordGeneratorPort {

    private static final String CHARS =
            "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789!@#$%";
    private static final int LENGTH = 12;

    private final SecureRandom random = new SecureRandom();

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }
}

// Deliberately excludes visually-confusing characters like 0/O, 1/l/I, since this password needs to be manually communicated to a real person