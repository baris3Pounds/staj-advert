package com.threepounds.advert.rolePermisionUser.controller;

import com.threepounds.advert.rolePermisionUser.dto.PermissionDto;
import com.threepounds.advert.rolePermisionUser.entity.Permission;
import com.threepounds.advert.rolePermisionUser.resource.PermissionResource;
import com.threepounds.advert.rolePermisionUser.service.PermissionService;
import com.threepounds.advert.rolePermisionUser.utils.mapper.PermissionMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {
    private final PermissionMapper permissionMapper;
    private final PermissionService permissionService;

    public PermissionController(PermissionMapper permissionMapper, PermissionService permissionService) {
        this.permissionMapper = permissionMapper;
        this.permissionService = permissionService;
    }

    @GetMapping("")
    public List<PermissionResource> getAllPermission(){
        List<Permission> permissions = permissionService.findAll();
        List<PermissionResource> permissionResourcesList = permissionMapper.PermissionListToPermissionResourceList(permissions);
        return permissionResourcesList;
    }

    @GetMapping("/{id}")
    public PermissionResource getPermission(@PathVariable UUID id){
        Permission permission = permissionService.findById(id);
        return permissionMapper.PermissionToPermissionResource(permission);
    }

    @PostMapping("")
    public PermissionResource createPermission(@RequestBody PermissionDto permissionDto){
        Permission permission = permissionMapper.PermissionDtoToPermission(permissionDto);

        Permission savedPermission = permissionService.save(permission);
        PermissionResource permissionResource = permissionMapper.PermissionToPermissionResource(
            savedPermission);
        return permissionResource;
    }

    @PutMapping("/{id}")
    public PermissionResource updatePermission(@PathVariable UUID id,@RequestBody PermissionDto permissionDto){
        Permission permission1 = permissionService.findById(id);
        permission1.setName(permissionDto.getName());
        Permission savedPermission = permissionService.save(permission1);
        return permissionMapper.PermissionToPermissionResource(savedPermission);
    }
}
