package com.karim.gdmr_backend.staff.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffProfileJpaRepository extends JpaRepository<StaffProfileEntity, Long> {
    Optional<StaffProfileEntity> findByUserId(Long userId);
}
