package com.karim.gdmr_backend.auth.adapter.in.web.dto;

import com.karim.gdmr_backend.auth.domain.model.PageResult;
import java.util.List;

public record PagedResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) { 
    public static <T> PagedResponse<T> from(PageResult<T> result) {
        return new PagedResponse<>(result.content(), result.page(), result.size(),
                result.totalElements(), result.totalPages());
    }
}