package com.octl3.api.service.impl;

import com.octl3.api.dto.response.RoleResponse;
import com.octl3.api.entity.Role;
import com.octl3.api.mapper.RoleMapper;
import com.octl3.api.repository.RoleRepository;
import com.octl3.api.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepo;
    RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAll() {
        List<Role> roles = roleRepo.findAll();
        List<RoleResponse> roleResponses = new ArrayList<>();
        for(Role role : roles) {
            roleResponses.add(roleMapper.toRoleResponse(role));
        }
        return roleResponses;
    }

    @Override
    public RoleResponse getRole(String roleName) {
        Role role = roleRepo.getReferenceById(roleName);
        return roleMapper.toRoleResponse(role);
    }
}
