package Finalproject.repository;

import Finalproject.entity.Permission;
import Finalproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}
