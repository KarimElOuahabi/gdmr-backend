package com.karim.gdmr_backend.doctor.adapter.in.web.dto;

import com.karim.gdmr_backend.doctor.domain.model.Speciality;

public record UpsertDoctorRequest(
        String phoneNumber,
        Speciality specialty,
        String qualifications,
        Integer yearsOfExperience,
        String workSite,
        String cnssNumber
) {}
