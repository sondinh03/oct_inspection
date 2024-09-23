package com.octl3.api.controller;

import com.octl3.api.commons.OctResponse;
import com.octl3.api.dto.request.UserCreationRequest;
import com.octl3.api.dto.request.UserUpdateRequest;
import com.octl3.api.dto.response.UserResponse;
import com.octl3.api.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    public OctResponse<List<UserResponse>> getAll() {
        return OctResponse.build(userService.getAll());
    }

    @GetMapping("/{id}")
    public OctResponse<UserResponse> getById(@PathVariable("id") Long userId) {
        UserResponse response = userService.getById(userId);
        return OctResponse.build(response);
    }

    @GetMapping("/my-info")
    public OctResponse<UserResponse> getMyInfo() {
        UserResponse response = userService.getMyInfo();
        return OctResponse.build(response);
    }

    @PostMapping()
    public OctResponse<UserResponse> create(@RequestBody UserCreationRequest request) {
        UserResponse response = userService.create(request);
        return OctResponse.build(response);
    }

    @PutMapping("/{userId}")
    public OctResponse<UserResponse> update(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        UserResponse response = userService.update(userId, request);
        return OctResponse.build(response);
    }

    @DeleteMapping("/{userId}")
    public OctResponse<String> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return OctResponse.build("Delete success.");
    }
}
