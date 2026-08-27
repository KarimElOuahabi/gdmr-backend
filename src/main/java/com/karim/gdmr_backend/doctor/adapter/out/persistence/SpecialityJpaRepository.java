package com.karim.gdmr_backend.doctor.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialityJpaRepository extends JpaRepository<SpecialityEntity, Long> {
    Optional<SpecialityEntity> findByName(String name);
}
