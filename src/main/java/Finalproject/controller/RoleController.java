package Finalproject.controller;


import Finalproject.dto.RoleDto;
import Finalproject.entity.Role;
import Finalproject.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/role")
@ControllerAdvice
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<RoleDto> createRole(@RequestBody @Valid Role role) {
        RoleDto roleDto = roleService.createRole(role);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping("/getAllRoles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> allRoles = roleService.getAllRoles();
        return ResponseEntity.ok(allRoles);
    }

    @GetMapping("/findRoleByName")
    public ResponseEntity<RoleDto> findRoleByName(@RequestParam String name) {
        RoleDto roleDto = roleService.findRoleByName(name);
        return ResponseEntity.ok(roleDto);
    }
}
