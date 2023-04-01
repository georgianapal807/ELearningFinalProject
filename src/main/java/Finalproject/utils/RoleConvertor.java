package Finalproject.utils;

import Finalproject.dto.RoleDto;
import Finalproject.entity.Role;

public class RoleConvertor {

    public static RoleDto entityToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
