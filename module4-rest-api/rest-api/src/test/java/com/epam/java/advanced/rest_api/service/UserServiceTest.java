package com.epam.java.advanced.rest_api.service;

import com.epam.java.advanced.rest_api.exception.ConflictException;
import com.epam.java.advanced.rest_api.exception.NotFoundException;
import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import com.epam.java.advanced.rest_api.model.entity.User;
import com.epam.java.advanced.rest_api.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void create_whenUsernameExists_throwsConflict() {
        UserRepository repo = mock(UserRepository.class);
        when(repo.existsByUsernameAndDeletedFalse("john")).thenReturn(true);

        UserService service = new UserService(repo);

        assertThrows(ConflictException.class,
                () -> service.create(new UserCreateRequest("john", "secretsecret", "john@example.com")));
    }

    @Test
    void getById_whenMissing_throwsNotFound() {
        UserRepository repo = mock(UserRepository.class);
        when(repo.findByIdAndDeletedFalse(1)).thenReturn(Optional.empty());

        UserService service = new UserService(repo);

        assertThrows(NotFoundException.class, () -> service.getById(1));
    }

    @Test
    void update_whenEmailExists_throwsConflict() {
        UserRepository repo = mock(UserRepository.class);
        User existing = new User();
        existing.setId(1);
        existing.setDeleted(false);
        when(repo.findByIdAndDeletedFalse(1)).thenReturn(Optional.of(existing));
        when(repo.existsByEmailAndDeletedFalseAndIdNot("x@example.com", 1)).thenReturn(true);

        UserService service = new UserService(repo);

        assertThrows(ConflictException.class,
                () -> service.update(1, new UserUpdateRequest("john", null, "x@example.com")));
    }

    @Test
    void delete_marksDeleted_andSaves() {
        UserRepository repo = mock(UserRepository.class);
        User existing = new User();
        existing.setId(1);
        existing.setDeleted(false);
        when(repo.findByIdAndDeletedFalse(1)).thenReturn(Optional.of(existing));
        when(repo.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        UserService service = new UserService(repo);

        service.delete(1);

        verify(repo).save(argThat(u -> Boolean.TRUE.equals(u.getDeleted())));
    }
}

