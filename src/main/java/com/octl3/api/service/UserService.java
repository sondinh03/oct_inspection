package com.octl3.api.service;

import com.octl3.api.dto.request.UserCreationRequest;
import com.octl3.api.dto.request.UserUpdateRequest;
import com.octl3.api.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse getById(Long userId);

    UserResponse getMyInfo();

    UserResponse create(UserCreationRequest request);

    UserResponse update(Long userId, UserUpdateRequest request);

    void delete(Long userId);
}
