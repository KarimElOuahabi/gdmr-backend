package com.karim.gdmr_backend.notification.application;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Short-lived, single-use tickets that let an EventSource (which can't send an
 * Authorization header) authenticate the SSE stream without ever putting the real
 * JWT in a URL/log. Issued via a normal Bearer-authenticated call, consumed once.
 */
@Component
public class SseTicketService {

    private static final long TICKET_TTL_SECONDS = 30;

    private record TicketEntry(Long userId, Instant expiresAt) {}

    private final Map<String, TicketEntry> tickets = new ConcurrentHashMap<>();

    public String issueTicket(Long userId) {
        purgeExpired();
        String ticket = UUID.randomUUID().toString();
        tickets.put(ticket, new TicketEntry(userId, Instant.now().plusSeconds(TICKET_TTL_SECONDS)));
        return ticket;
    }

    public Optional<Long> consumeTicket(String ticket) {
        TicketEntry entry = tickets.remove(ticket);
        if (entry == null || entry.expiresAt().isBefore(Instant.now())) {
            return Optional.empty();
        }
        return Optional.of(entry.userId());
    }

    private void purgeExpired() {
        Instant now = Instant.now();
        tickets.entrySet().removeIf(e -> e.getValue().expiresAt().isBefore(now));
    }
}
