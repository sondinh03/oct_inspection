package com.octl3.api.service;

import com.octl3.api.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAll();
}
