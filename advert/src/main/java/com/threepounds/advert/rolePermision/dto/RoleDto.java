package com.threepounds.advert.rolePermision.dto;

import java.util.List;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
    private UUID userId;
    private List<UUID> permissionIds;

}
