package com.epam.java.advanced.rest_api.mapper;

import com.epam.java.advanced.rest_api.model.dto.UserCreateRequest;
import com.epam.java.advanced.rest_api.model.dto.UserResponseDto;
import com.epam.java.advanced.rest_api.model.dto.UserUpdateRequest;
import com.epam.java.advanced.rest_api.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    User createUser(UserCreateRequest newUserRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponseDto toUserResponse(User user);
}
