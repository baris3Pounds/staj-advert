package com.threepounds.advert.rolePermision.service;

import com.threepounds.advert.rolePermision.entity.Permission;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements PermissionService{
    @Override
    public Permission save(Permission permission) {
        return null;
    }

    @Override
    public Permission findByName(UUID id) {
        return null;
    }

    @Override
    public List<Permission> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(UUID id) {

    }
}
