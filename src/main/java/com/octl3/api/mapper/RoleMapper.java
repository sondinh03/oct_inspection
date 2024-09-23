package com.octl3.api.mapper;

import com.octl3.api.dto.request.RoleRequest;
import com.octl3.api.dto.response.RoleResponse;
import com.octl3.api.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponse(Role role);

    Role toUser(RoleRequest request);

    void updateRole(@MappingTarget Role role, RoleRequest request);
}
