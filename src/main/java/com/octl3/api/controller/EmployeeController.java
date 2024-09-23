package com.octl3.api.controller;

import com.octl3.api.commons.OctResponse;
import com.octl3.api.dto.request.UserCreationRequest;
import com.octl3.api.dto.request.UserUpdateRequest;
import com.octl3.api.dto.response.EmployeeResponse;
import com.octl3.api.dto.response.UserResponse;
import com.octl3.api.service.EmployeeService;
import com.octl3.api.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping
    public OctResponse<List<EmployeeResponse>> getAll() {
        return OctResponse.build(employeeService.getAll());
    }
}
