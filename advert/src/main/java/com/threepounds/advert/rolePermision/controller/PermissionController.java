package com.threepounds.advert.rolePermision.controller;

import com.threepounds.advert.rolePermision.dto.PermissionDto;
import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.resource.PermissionResource;
import com.threepounds.advert.rolePermision.service.PermissionService;
import com.threepounds.advert.rolePermision.utils.mapper.PermissionMapper;
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
    public Permission getPermission(@PathVariable UUID id){
        return permissionService.findById(id);
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
    public Permission updatePermission(@PathVariable UUID uuid,@RequestBody PermissionDto permissionDto){
        Permission permission1 = permissionService.findById(uuid);
        permission1.setName(permissionDto.getName());
       return permissionService.save(permission1);
    }
}
