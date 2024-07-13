package com.threepounds.advert.rolePermision.controller;

import com.threepounds.advert.rolePermision.utils.mapper.RoleMapper;
import com.threepounds.advert.rolePermision.dto.RoleDto;
import com.threepounds.advert.rolePermision.entity.Role;
import com.threepounds.advert.rolePermision.repository.PermissionRepository;
import com.threepounds.advert.rolePermision.repository.RoleRepository;
import com.threepounds.advert.user.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final RoleMapper roleMapper;

    public RoleController(UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMapper = roleMapper;
    }

    @PostMapping("")
    public Role createRole(@RequestBody RoleDto roleDto){
        Role role = roleMapper.RoleDtoToRole(roleDto);

    }

}
