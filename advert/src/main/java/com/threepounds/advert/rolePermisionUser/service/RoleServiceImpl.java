package com.threepounds.advert.rolePermisionUser.service;

import com.threepounds.advert.rolePermisionUser.entity.Permission;
import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.repository.RoleRepository;
import com.threepounds.advert.rolePermisionUser.repository.PermissionRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("The role couldn't be found"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role addPermissionToRole(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow();
        Permission permission = permissionRepository.findById(permissionId).orElseThrow();
        role.getPermissions().add(permission);
        roleRepository.save(role);
        return role;
    }

    @Override
    public Optional<Role> findByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
