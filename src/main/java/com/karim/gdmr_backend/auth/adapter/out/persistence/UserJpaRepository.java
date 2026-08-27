package com.karim.gdmr_backend.auth.adapter.out.persistence;

import com.karim.gdmr_backend.auth.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByRole(RoleEntity role);
    long countByRoleAndActive(RoleEntity role, boolean active);
}
