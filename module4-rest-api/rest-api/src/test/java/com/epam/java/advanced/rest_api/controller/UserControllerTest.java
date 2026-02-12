package com.epam.java.advanced.rest_api.controller;

import com.epam.java.advanced.rest_api.exception.NotFoundException;
import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    void create_returns201_andLocation() throws Exception {
        UserResponseDto resp = new UserResponseDto(1, "john", "john@example.com", LocalDateTime.now(), LocalDateTime.now());
        when(userService.create(any(UserCreateRequest.class))).thenReturn(resp);

        UserCreateRequest req = new UserCreateRequest("john", "secretsecret", "john@example.com");

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/users/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    void create_validationError_returns400() throws Exception {
        // username blank + invalid email
        String body = "{\"username\":\"\",\"password\":\"secretsecret\",\"email\":\"not-an-email\"}";

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void getById_notFound_returns404() throws Exception {
        when(userService.getById(42)).thenThrow(new NotFoundException("User not found"));

        mvc.perform(get("/users/42"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}

