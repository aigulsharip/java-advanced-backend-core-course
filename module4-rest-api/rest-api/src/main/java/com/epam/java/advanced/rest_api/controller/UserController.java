package com.epam.java.advanced.rest_api.controller;

import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import com.epam.java.advanced.rest_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateRequest request) {
        UserResponseDto response = userService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserResponseDto> list() {
        return userService.list();
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
