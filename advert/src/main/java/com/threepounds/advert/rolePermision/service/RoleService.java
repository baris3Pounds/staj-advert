package com.threepounds.advert.rolePermision.service;

import com.threepounds.advert.rolePermision.entity.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role save(Role role);
    Role findByName(UUID id);
    List<Role> findAll();
    void deleteById(UUID id);
}
