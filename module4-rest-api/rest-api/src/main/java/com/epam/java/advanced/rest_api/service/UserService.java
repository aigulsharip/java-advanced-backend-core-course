package com.epam.java.advanced.rest_api.service;

import com.epam.java.advanced.rest_api.exception.ConflictException;
import com.epam.java.advanced.rest_api.exception.NotFoundException;
import com.epam.java.advanced.rest_api.mapper.UserMapper;
import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import com.epam.java.advanced.rest_api.model.entity.User;
import com.epam.java.advanced.rest_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDto create(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.username())) {
            throw new ConflictException("Username already exists");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email already exists");
        }
        User newUser = userMapper.createUser(request);
        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return userMapper.toUserResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> list() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Transactional
    public UserResponseDto update(Integer id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (userRepository.existsByUsernameAndIdNot(request.username(), id)) {
            throw new ConflictException("Username already exists");
        }
        if (userRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new ConflictException("Email already exists");
        }

        userMapper.updateUser(user, request);
        user.setUpdated(LocalDateTime.now());

        User saved = userRepository.save(user);
        return userMapper.toUserResponse(saved);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setUpdated(LocalDateTime.now());
        userRepository.save(user);
        userRepository.delete(user);
    }
}
