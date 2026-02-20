package com.epam.java.advanced.rest_api.mapper;

import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import com.epam.java.advanced.rest_api.model.entity.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-12T13:44:42+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUser(UserCreateRequest newUserRequest) {
        if ( newUserRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( newUserRequest.username() );
        user.setPassword( newUserRequest.password() );
        user.setEmail( newUserRequest.email() );

        return user;
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.username() != null ) {
            user.setUsername( request.username() );
        }
        if ( request.password() != null ) {
            user.setPassword( request.password() );
        }
        if ( request.email() != null ) {
            user.setEmail( request.email() );
        }
    }

    @Override
    public UserResponseDto toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        Integer id = null;
        String username = null;
        String email = null;
        LocalDateTime created = null;
        LocalDateTime updated = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        created = user.getCreated();
        updated = user.getUpdated();

        UserResponseDto userResponseDto = new UserResponseDto( id, username, email, created, updated );

        return userResponseDto;
    }
}
