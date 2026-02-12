package com.epam.java.advanced.rest_api.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotBlank @Size(max = 30) String username,
        @Size(max = 80) String password,
        @NotBlank @Email @Size(max = 50) String email
) {
}
