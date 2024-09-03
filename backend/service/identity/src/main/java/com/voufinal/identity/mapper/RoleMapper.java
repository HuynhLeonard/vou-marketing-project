package com.voufinal.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.voufinal.identity.dto.request.RoleRequest;
import com.voufinal.identity.dto.response.RoleResponse;
import com.voufinal.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
