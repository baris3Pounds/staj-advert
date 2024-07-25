package com.threepounds.advert.rolePermisionUser.service;

import com.threepounds.advert.rolePermisionUser.entity.Permission;
import com.threepounds.advert.rolePermisionUser.repository.PermissionRepository;
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
    public Permission findById(UUID id) {
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

    @Override
    public List<Permission> findByIdList(List<UUID> ids) {
       return permissionRepository.findAllById(ids);
    }
}
