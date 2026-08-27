package com.karim.gdmr_backend.employee.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findById(Long employeeId);
    Optional<EmployeeEntity> findByUserId(Long userId);
    Page<EmployeeEntity> findAll(Pageable pageable);

    // Matches on first name, last name, first+last combined, CIN, and CNSS
    // number — so typing "john smith" (both names at once), a CIN, or a CNSS
    // number all find the right employee, not just a single-field prefix match.
    @Query(value = """
            SELECT e.* FROM employees e JOIN users u ON u.id = e.user_id
            WHERE LOWER(CONCAT(u.first_name, ' ', u.last_name)) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.first_name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.last_name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.cin) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(e.cnss_number) LIKE LOWER(CONCAT('%', :search, '%'))
            """,
            countQuery = """
            SELECT count(*) FROM employees e JOIN users u ON u.id = e.user_id
            WHERE LOWER(CONCAT(u.first_name, ' ', u.last_name)) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.first_name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.last_name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(u.cin) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(e.cnss_number) LIKE LOWER(CONCAT('%', :search, '%'))
            """,
            nativeQuery = true)
    Page<EmployeeEntity> search(@Param("search") String search, Pageable pageable);
}