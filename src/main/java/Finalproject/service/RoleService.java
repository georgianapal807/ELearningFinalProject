package Finalproject.service;

import Finalproject.dto.RoleDto;
import Finalproject.entity.Role;

import java.util.List;

public interface RoleService {

    RoleDto createRole(Role role);

    RoleDto findRoleByName(String name);

    List<RoleDto> getAllRoles();
}
