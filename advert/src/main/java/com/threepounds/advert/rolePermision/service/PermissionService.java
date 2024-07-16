package com.threepounds.advert.rolePermision.service;

import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.entity.Role;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    Permission save(Permission permission);
    Permission findById(UUID id);
    List<Permission> findAll();
    void deleteById(UUID id);

    List<Permission> findByIdList(List<UUID> ids);
}
