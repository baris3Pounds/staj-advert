package com.threepounds.advert.rolePermision.service;

import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.entity.Role;
import com.threepounds.advert.rolePermision.repository.RoleRepository;
import com.threepounds.advert.rolePermision.repository.PermissionRepository;
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
    public void addPermissionToRole(UUID roleId, Permission permission) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.getPermissions().add(permission);
    }
}
