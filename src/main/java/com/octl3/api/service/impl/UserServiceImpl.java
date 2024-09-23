package com.octl3.api.service.impl;

import com.octl3.api.commons.exception.OctEntityNotFoundException;
import com.octl3.api.commons.exception.OctException;
import com.octl3.api.commons.suberror.ApiMessageError;
import com.octl3.api.dto.request.UserCreationRequest;
import com.octl3.api.dto.request.UserUpdateRequest;
import com.octl3.api.dto.response.UserResponse;
import com.octl3.api.entity.User;
import com.octl3.api.mapper.UserMapper;
import com.octl3.api.repository.UserRepository;
import com.octl3.api.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.octl3.api.commons.exception.ErrorMessages.*;
import static com.octl3.api.consts.MessageConst.USER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public List<UserResponse> getAll() {
        return  userRepo.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public UserResponse getById(Long userId) {
        return userRepo.findById(userId)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new OctEntityNotFoundException(NOT_FOUND_USER, new ApiMessageError(USER_NOT_FOUND)));
    }

    @Override
    public UserResponse getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepo.findByUsername(name).orElseThrow(() -> new OctException(NOT_FOUND_USER));
        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public UserResponse create(UserCreationRequest request) {
        //Kiểm tra trùng username
        if(userRepo.existsByUsername(request.getUsername())) {
            throw new OctException(INVALID_VALUE);
        }

        User user = userMapper.toUser(request);

        //Mã hóa password, kiểm tra độ dài tối thiểu
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        //Đặt giá trị cho role
        user.setRole(request.getRole() != null && !request.getRole().isEmpty() ? request.getRole() : "EMPLOYEE");

        try {
            userRepo.save(user);
        } catch (OctException e) {
            throw new OctException(SAVE_DATABASE_ERROR);
        }
        return userMapper.toUserResponse(user);
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepo.findById(userId).orElseThrow(() -> new OctException(NOT_FOUND));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            userRepo.save(user);
        } catch (OctException e) {
            throw new OctException(SAVE_DATABASE_ERROR);
        }
        return userMapper.toUserResponse(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new OctEntityNotFoundException(NOT_FOUND_USER, new ApiMessageError(USER_NOT_FOUND));
        }
        try {
            userRepo.deleteById(userId);
            log.info("User with ID {} has been deleted.", userId);
        } catch (OctException e) {
            log.error("Error occurred while deleting user with ID {}: {}", userId, e.getMessage());
            throw new OctException(INTERNAL_SERVER_ERROR);
        }
    }
}

/*
 *  Việc cần làm:
 *  + Role admin update mọi thông tin của user
 *  + Người dùng chỉ update được thông tin họ tên của mình, thay đổi mật khẩu, không thể thay đổi role.
 *
 */
