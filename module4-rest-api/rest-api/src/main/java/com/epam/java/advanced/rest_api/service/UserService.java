package com.epam.java.advanced.rest_api.service;

import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponseDto create(UserCreateRequest request);

    UserResponseDto getById(Integer id);

    Page<UserResponseDto> listPagination(Pageable pageable);

    List<UserResponseDto> list();

    UserResponseDto update(Integer id, UserUpdateRequest request);

    void delete(Integer id);

}
