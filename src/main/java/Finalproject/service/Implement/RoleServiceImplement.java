package Finalproject.service.Implement;

import Finalproject.exception.role.RoleNotFoundException;
import Finalproject.dto.RoleDto;
import Finalproject.entity.Role;
import Finalproject.repository.RoleRepository;
import Finalproject.service.RoleService;
import Finalproject.utils.RoleConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplement(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRole(Role newRole) {
        Role role = roleRepository.save(newRole);
        RoleDto roleDto = RoleConvertor.entityToDto(role);
        return roleDto;
    }

    @Override
    public RoleDto findRoleByName(String name) {
        Role role;
        role = roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role with name " + name + " not found!"));
        return RoleConvertor.entityToDto(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<RoleDto> roleDtos = new ArrayList<>();
        roleRepository.findAll().forEach((role -> roleDtos.add(RoleConvertor.entityToDto(role))));
        return roleDtos;
    }
}
