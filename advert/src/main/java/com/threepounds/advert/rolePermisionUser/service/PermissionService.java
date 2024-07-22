package com.threepounds.advert.rolePermisionUser.service;

import com.threepounds.advert.rolePermisionUser.entity.Permission;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    Permission save(Permission permission);
    Permission findById(UUID id);
    List<Permission> findAll();
    void deleteById(UUID id);

    List<Permission> findByIdList(List<UUID> ids);
}
