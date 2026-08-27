package com.karim.gdmr_backend.visit.domain.port.in;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import com.karim.gdmr_backend.visit.domain.model.Visit;
import com.karim.gdmr_backend.visit.domain.model.VisitStatus;

public interface ListVisitsUseCase {

    PageResult<Visit> listVisits(ListVisitsQuery query);

    record ListVisitsQuery(Long employeeId, Long doctorId, VisitStatus status, int page, int size) {}
}