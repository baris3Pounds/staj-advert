package com.threepounds.advert.rolePermision.service;

import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.entity.Role;
import com.threepounds.advert.rolePermision.repository.RoleRepository;

import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id).orElseThrow(()-> new RuntimeException("The role couldnt find"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {

    }
}
