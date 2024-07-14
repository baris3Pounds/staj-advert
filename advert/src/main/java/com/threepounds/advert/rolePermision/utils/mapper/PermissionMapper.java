package com.threepounds.advert.rolePermision.utils.mapper;

import com.threepounds.advert.rolePermision.dto.PermissionDto;
import com.threepounds.advert.rolePermision.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission PermissionDtoToPermission(PermissionDto permissionDto);
}
