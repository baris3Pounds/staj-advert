package com.threepounds.advert.rolePermision.utils.mapper;

import com.threepounds.advert.rolePermision.dto.PermissionDto;
import com.threepounds.advert.rolePermision.entity.Permission;
import com.threepounds.advert.rolePermision.resource.PermissionResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission PermissionDtoToPermission(PermissionDto permissionDto);

    @Mapping(source = "roles", target = "roles")
    PermissionResource PermissionToPermissionResource(Permission permission);

    List<PermissionResource> PermissionListToPermissionResourceList(List<Permission> permissions);
}
