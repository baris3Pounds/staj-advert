package com.threepounds.advert.rolePermision.utils.mapper;

import com.threepounds.advert.rolePermision.dto.RoleDto;
import com.threepounds.advert.rolePermision.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id" , ignore = true)
    Role RoleDtoToRole(RoleDto roleDto);
}
