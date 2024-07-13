package com.threepounds.advert.rolePermision;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id" , ignore = true)
    Role RoleDtoToRole(RoleDto roleDto);
}
