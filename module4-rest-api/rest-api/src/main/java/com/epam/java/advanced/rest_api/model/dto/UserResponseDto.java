package com.epam.java.advanced.rest_api.model.dto;

import java.time.LocalDateTime;


public record UserResponseDto(
        Integer id,
        String username,
        String email,
        LocalDateTime created,
        LocalDateTime updated
) {
}

