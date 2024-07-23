package com.threepounds.advert.rolePermisionUser.controller;

import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.dto.RoleDto;
import com.threepounds.advert.rolePermisionUser.entity.Permission;
import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.resource.RoleResource;
import com.threepounds.advert.rolePermisionUser.service.PermissionService;
import com.threepounds.advert.rolePermisionUser.service.RoleService;
import com.threepounds.advert.rolePermisionUser.utils.mapper.RoleMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
    public List<RoleResource> getAll(){
    List<Role> list = roleService.findAll();
    List<Permission> permissions = permissionService.findAll();
    return roleMapper.RoleListToRoleResourceList(list);
    }

    @GetMapping("/{id}")
    public RoleResource getRole(@PathVariable UUID id){
        Role role = roleService.findById(id);
        return roleMapper.RoleToRoleResource(role);
    }

    /*

    @PostMapping("")
    public Role createRole(@RequestBody RoleDto roleDto){
        List<Permission> permissions = permissionService.findByIdList(roleDto.getPermissionIds());
        Role role = roleMapper.RoleDtoToRole(roleDto);
        role.setPermissions(permissions);
        return roleService.save(role);
    }

     */

    @PostMapping("")
    public ResponseEntity<GeneralResponse<RoleResource>> createRole(@RequestBody RoleDto roleDto){

        Role role = roleMapper.RoleDtoToRole(roleDto);
        if(!CollectionUtils.isEmpty(roleDto.getPermissionIds())){
            List<Permission> permissions = permissionService.findByIdList(roleDto.getPermissionIds());
            role.setPermissions(permissions);
        }

        roleService.save(role);
        GeneralResponse<RoleResource> objectGeneralResponse = new GeneralResponse<>();
        objectGeneralResponse.setErrors(null);
        objectGeneralResponse.setData(roleMapper.RoleToRoleResource(role));
        return ResponseEntity.ok().body(objectGeneralResponse);
    }

    @PutMapping("/{id}")
    public RoleResource updateRole(@PathVariable UUID id, @RequestBody RoleDto roleDto){
        List<Permission> permissions = permissionService.findByIdList(roleDto.getPermissionIds());
        Role role = roleService.findById(id);
        Role mappedRole = roleMapper.RoleDtoToRole(roleDto);
        mappedRole.setId(role.getId());
        role.setPermissions(permissions);
         Role role1 = roleService.save(role);
        RoleResource roleResource = roleMapper.RoleToRoleResource(role1);
        return roleResource;
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
