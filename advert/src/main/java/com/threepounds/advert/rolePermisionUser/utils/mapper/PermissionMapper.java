package com.threepounds.advert.rolePermisionUser.utils.mapper;

import com.threepounds.advert.rolePermisionUser.dto.PermissionDto;
import com.threepounds.advert.rolePermisionUser.entity.Permission;
import com.threepounds.advert.rolePermisionUser.resource.PermissionResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission PermissionDtoToPermission(PermissionDto permissionDto);

    PermissionResource PermissionToPermissionResource(Permission permission);

    List<PermissionResource> PermissionListToPermissionResourceList(List<Permission> permissions);
}
