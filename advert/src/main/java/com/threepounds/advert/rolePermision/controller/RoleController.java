package com.threepounds.advert.rolePermision.controller;

import com.threepounds.advert.rolePermision.dto.RoleDto;
import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.entity.Role;
import com.threepounds.advert.rolePermision.service.PermissionService;
import com.threepounds.advert.rolePermision.service.RoleService;
import com.threepounds.advert.rolePermision.utils.mapper.RoleMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    private final PermissionService permissionService;

    public RoleController(RoleService roleService, RoleMapper roleMapper,
        PermissionService permissionService) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
        this.permissionService = permissionService;
    }

    @GetMapping("")
    public List<Role> getAll(){
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role getRole(@PathVariable UUID id){
        return roleService.findById(id);
    }



    @PostMapping("")
    public Role createRole(@RequestBody RoleDto roleDto){
        List<Permission> permissions = permissionService.findByIdList(roleDto.getPermissionIds());
        Role role = roleMapper.RoleDtoToRole(roleDto);
        role.setPermissions(permissions);
        return roleService.save(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable UUID id, @RequestBody RoleDto roleDto){
        List<Permission> permissions = permissionService.findByIdList(roleDto.getPermissionIds());
        Role role = roleService.findById(id);
        Role mappedRole = roleMapper.RoleDtoToRole(roleDto);
        mappedRole.setId(role.getId());
        role.setPermissions(permissions);
        return roleService.save(role);
    }

//    @PostMapping("/{id}/permissions/{id2}")
//    public Role addPermissionToRole(@PathVariable UUID id , @PathVariable UUID id2){
//        return roleService.addPermissionToRole(id, id2);
//
//    }



    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable UUID id) {
        roleService.deleteById(id);
    }
}
