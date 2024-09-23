package com.octl3.api.mapper;

import com.octl3.api.dto.request.UserCreationRequest;
import com.octl3.api.dto.request.UserUpdateRequest;
import com.octl3.api.dto.response.UserResponse;
import com.octl3.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    @Mapping(target = "userId", ignore = true)
    User toUser(UserCreationRequest request);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
