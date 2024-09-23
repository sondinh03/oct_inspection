package com.octl3.api.service.impl;

import com.octl3.api.commons.exception.OctEntityNotFoundException;
import com.octl3.api.commons.exception.OctException;
import com.octl3.api.commons.suberror.ApiMessageError;
import com.octl3.api.dto.request.UserCreationRequest;
import com.octl3.api.dto.request.UserUpdateRequest;
import com.octl3.api.dto.response.EmployeeResponse;
import com.octl3.api.dto.response.UserResponse;
import com.octl3.api.entity.User;
import com.octl3.api.mapper.EmployeeMapper;
import com.octl3.api.mapper.UserMapper;
import com.octl3.api.repository.EmployeeRepository;
import com.octl3.api.repository.UserRepository;
import com.octl3.api.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepo;
    EmployeeMapper employeeMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public List<EmployeeResponse> getAll() {
        return  employeeRepo.findAll().stream()
                .map(employeeMapper::toEmployeeResponse)
                .toList();
    }

}
