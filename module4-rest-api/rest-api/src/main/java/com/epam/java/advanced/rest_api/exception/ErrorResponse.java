package com.epam.java.advanced.rest_api.exception;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
@Builder
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldError> fieldErrors
) {
    public record FieldError(String field, String message) {
    }
}

