package com.karim.gdmr_backend.employee.adapter.in.web;

import com.karim.gdmr_backend.auth.adapter.in.web.dto.PagedResponse;
import com.karim.gdmr_backend.auth.adapter.in.web.dto.UserResponse;
import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.auth.domain.model.Role;
import com.karim.gdmr_backend.auth.domain.model.User;
import com.karim.gdmr_backend.auth.domain.model.UserPrincipal;
import com.karim.gdmr_backend.auth.domain.port.in.GetCurrentUserUseCase;
import com.karim.gdmr_backend.auth.domain.port.in.GetUsersByIdsUseCase;
import com.karim.gdmr_backend.employee.adapter.in.web.dto.EmployeeProfileResponse;
import com.karim.gdmr_backend.employee.adapter.in.web.dto.EmployeeResponse;
import com.karim.gdmr_backend.employee.adapter.in.web.dto.UpsertEmployeeRequest;
import com.karim.gdmr_backend.employee.domain.model.Employee;
import com.karim.gdmr_backend.employee.domain.port.in.GetMyProfileUseCase;
import com.karim.gdmr_backend.employee.domain.port.in.ListEmployeesUseCase;
import com.karim.gdmr_backend.employee.domain.port.in.UpsertEmployeeUseCase;
import com.karim.gdmr_backend.shared.security.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.karim.gdmr_backend.employee.domain.port.in.GetEmployeeByIdUseCase;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final GetMyProfileUseCase getMyProfileUseCase;
    private final UpsertEmployeeUseCase upsertEmployeeUseCase;
    private final ListEmployeesUseCase listEmployeesUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final GetUsersByIdsUseCase getUsersByIdsUseCase;
    private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;

    public EmployeeController(GetMyProfileUseCase getMyProfileUseCase,
                              UpsertEmployeeUseCase upsertEmployeeUseCase,
                              ListEmployeesUseCase listEmployeesUseCase,
                              GetCurrentUserUseCase getCurrentUserUseCase,
                              GetUsersByIdsUseCase getUsersByIdsUseCase,
                              GetEmployeeByIdUseCase getEmployeeByIdUseCase) {
        this.getMyProfileUseCase = getMyProfileUseCase;
        this.upsertEmployeeUseCase = upsertEmployeeUseCase;
        this.listEmployeesUseCase = listEmployeesUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.getUsersByIdsUseCase = getUsersByIdsUseCase;
        this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
    }

    @GetMapping("/api/employee/profile")
    public ResponseEntity<EmployeeProfileResponse> getMyProfile(@AuthenticationPrincipal UserPrincipal principal) {
        Employee employee = getMyProfileUseCase.getMyProfile(principal.userId());
        User user = getCurrentUserUseCase.getCurrentUser(principal.userId());
        return ResponseEntity.ok(EmployeeProfileResponse.from(user, employee));
    }

    @GetMapping("/api/employee/profile/{employeeId}")
    public ResponseEntity<EmployeeProfileResponse> getEmployeeProfile(@PathVariable Long employeeId) {
        Employee employee = getEmployeeByIdUseCase.getEmployeeById(employeeId);
        User targetUser = getCurrentUserUseCase.getCurrentUser(employee.getUserId());
        return ResponseEntity.ok(EmployeeProfileResponse.from(targetUser, employee));
    }

    @GetMapping("/api/employee/profile/user/{userId}")
    public ResponseEntity<EmployeeProfileResponse> getEmployeeProfileByUserId(@PathVariable Long userId) {
        Employee employee = getMyProfileUseCase.getMyProfile(userId);
        User targetUser = getCurrentUserUseCase.getCurrentUser(userId);
        return ResponseEntity.ok(EmployeeProfileResponse.from(targetUser, employee));
    }

    @GetMapping("/api/employees")
    public ResponseEntity<PagedResponse<EmployeeProfileResponse>> listEmployees(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        PageResult<Employee> result = listEmployeesUseCase.listEmployees(
                new ListEmployeesUseCase.ListEmployeesQuery(search, page, size));

        List<Long> userIds = result.content().stream().map(Employee::getUserId).toList();
        Map<Long, User> usersById = getUsersByIdsUseCase.getUsersByIds(userIds).stream()
                .collect(Collectors.toMap(User::getId, u -> u));

        List<EmployeeProfileResponse> content = result.content().stream()
                .map(emp -> EmployeeProfileResponse.from(usersById.get(emp.getUserId()), emp))
                .toList();

        PageResult<EmployeeProfileResponse> mapped = new PageResult<>(
                content, result.page(), result.size(), result.totalElements(), result.totalPages());

        return ResponseEntity.ok(PagedResponse.from(mapped));
    }

    @PutMapping("/api/employee/{userId}")
    public ResponseEntity<EmployeeResponse> upsertEmployee(
            @PathVariable Long userId,
            @RequestBody UpsertEmployeeRequest request) {

        Employee saved = upsertEmployeeUseCase.upsertEmployee(
                new UpsertEmployeeUseCase.UpsertEmployeeCommand(
                        userId, request.birthDate(), request.department(),
                        request.phoneNumber(), request.jobTitle(), request.hireDate(),
                        request.cnssNumber()
                )
        );

        return ResponseEntity.ok(EmployeeResponse.from(saved));
    }

    private Long extractUserId(Authentication authentication) {
        return ((AuthenticatedUser) authentication.getPrincipal()).userId();
    }
}