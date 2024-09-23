package com.octl3.api.service;

import com.octl3.api.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAll();

    RoleResponse getRole(String roleName);
}
