package com.threepounds.advert.rolePermision.utils.mapper;

import com.threepounds.advert.rolePermision.dto.RoleDto;
import com.threepounds.advert.rolePermision.entity.Role;
import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.resource.RoleResource;
import com.threepounds.advert.rolePermision.resource.PermissionResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    Role RoleDtoToRole(RoleDto roleDto);

    @Mapping(source = "permissions", target = "permissions")
    RoleResource RoleToRoleResource(Role role);

    List<RoleResource> RoleListToRoleResourceList(List<Role> roles);

    // Permission list mapping
    List<PermissionResource> PermissionsToPermissionResources(List<Permission> permissions);
}
