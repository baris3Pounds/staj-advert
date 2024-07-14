package com.threepounds.advert.rolePermision.controller;

import com.threepounds.advert.rolePermision.dto.RoleDto;
import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.entity.Role;
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

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
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
        Role role = roleMapper.RoleDtoToRole(roleDto);
        return roleService.save(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable UUID id, @RequestBody RoleDto roleDto){
        Role role = roleService.findById(id);
        role.setName(roleDto.getName());
        return roleService.save(role);
    }

    @PostMapping("/{id}/permissions/{id2}")
    public Role addPermissionToRole(@PathVariable UUID id , @PathVariable UUID id2){
        return roleService.addPermissionToRole(id, id2);

    }



    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable UUID id) {
        roleService.deleteById(id);
    }
}
