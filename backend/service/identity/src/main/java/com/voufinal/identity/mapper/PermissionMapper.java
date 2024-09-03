package com.voufinal.identity.mapper;

import org.mapstruct.Mapper;

import com.voufinal.identity.dto.request.PermissionRequest;
import com.voufinal.identity.dto.response.PermissionResponse;
import com.voufinal.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
