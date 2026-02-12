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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND_ERROR = "User not found with id: ";
    private static final String USERNAME_EXISTS_ERROR = "Username already exists: ";
    private static final String EMAIL_EXISTS_ERROR = "Email already exists: ";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto create(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.username())) {
            throw new ConflictException(USERNAME_EXISTS_ERROR);
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException(EMAIL_EXISTS_ERROR);
        }
        User newUser = userMapper.createUser(request);
        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR + id));
        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDto> listPagination(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toUserResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> list() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDto update(Integer id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR + id));

        if (userRepository.existsByUsernameAndIdNot(request.username(), id)) {
            throw new ConflictException(USERNAME_EXISTS_ERROR);
        }
        if (userRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new ConflictException(EMAIL_EXISTS_ERROR);
        }

        userMapper.updateUser(user, request);
        user.setUpdated(LocalDateTime.now());

        User saved = userRepository.save(user);
        return userMapper.toUserResponse(saved);
    }
    @Override
    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_ERROR));

        user.setUpdated(LocalDateTime.now());
        userRepository.save(user);
        userRepository.delete(user);
    }
}
