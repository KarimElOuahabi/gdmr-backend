package com.karim.gdmr_backend.doctor.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Doctor {

    private final Long id;
    private final Long userId;
    private final String phoneNumber;
    private final Speciality specialty;
    private final String qualifications;
    private final Integer yearsOfExperience;
    private final String workSite;
    private final String cnssNumber;

    public static Doctor createNew(Long id, Long userId, String phoneNumber, Speciality specialty,
                                   String qualifications, Integer yearsOfExperience, String workSite,
                                   String cnssNumber) {
        return new Doctor(null, userId, phoneNumber, specialty, qualifications, yearsOfExperience, workSite, cnssNumber);
    }
}
