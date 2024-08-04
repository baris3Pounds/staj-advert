package com.threepounds.advert.rolePermisionUser.service;

import com.threepounds.advert.rolePermisionUser.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Role save(Role role);
    Role findById(UUID id);
    List<Role> findAll();
    void deleteById(UUID id);
    Role addPermissionToRole(UUID id, UUID id2);

    Optional<Role> findByCode(String code);
}
