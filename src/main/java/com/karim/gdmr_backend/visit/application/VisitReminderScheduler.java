package com.karim.gdmr_backend.visit.application;

import com.karim.gdmr_backend.visit.domain.port.in.SendVisitRemindersUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VisitReminderScheduler {

    private final SendVisitRemindersUseCase sendVisitRemindersUseCase;

    public VisitReminderScheduler(SendVisitRemindersUseCase sendVisitRemindersUseCase) {
        this.sendVisitRemindersUseCase = sendVisitRemindersUseCase;
    }

    // Tous les jours à 8h — rappelle les visites prévues dans les prochaines 24h
    @Scheduled(cron = "0 0 8 * * *")
    public void sendDailyReminders() {
        sendVisitRemindersUseCase.sendReminders();
    }
}
