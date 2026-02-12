package com.epam.java.advanced.rest_api.service;

import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponseDto create(UserCreateRequest request);

    UserResponseDto getById(Integer id);

    Page<UserResponseDto> list(Pageable pageable);
    UserResponseDto update(Integer id, UserUpdateRequest request);
    void delete(Integer id);

}
