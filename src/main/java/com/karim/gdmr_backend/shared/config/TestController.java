package com.karim.gdmr_backend.shared.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/admin/ping")
    public ResponseEntity<String> adminPing() {
        return ResponseEntity.ok("Hello ADMIN");
    }

    @GetMapping("/api/doctor/ping")
    public ResponseEntity<String> doctorPing() {
        return ResponseEntity.ok("Hello DOCTOR");
    }

    @GetMapping("/api/hr/ping")
    public ResponseEntity<String> hrPing() {
        return ResponseEntity.ok("Hello HR");
    }

    @GetMapping("/api/employee/ping")
    public ResponseEntity<String> employeePing() {
        return ResponseEntity.ok("Hello EMPLOYEE");
    }

}
