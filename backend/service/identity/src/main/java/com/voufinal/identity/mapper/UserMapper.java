package com.voufinal.identity.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.voufinal.identity.dto.request.UserCreationRequest;
import com.voufinal.identity.dto.request.UserUpdateRequest;
import com.voufinal.identity.dto.response.UserResponse;
import com.voufinal.identity.entity.Role;
import com.voufinal.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    default Set<Role> map(List<String> roles) {
        if (roles == null) {
            return null;
        }
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            Role role = new Role();
            role.setName(roleName);
            roleSet.add(role);
        }
        return roleSet;
    }
}
