package com.threepounds.advert.rolePermision.service;

import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService{
    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findByName(UUID id) {
        return permissionRepository.findById(id).orElseThrow(()-> new RuntimeException("permission not found"));
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        permissionRepository.deleteById(id);
    }
}
