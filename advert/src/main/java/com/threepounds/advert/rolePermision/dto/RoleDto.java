package com.threepounds.advert.rolePermision.dto;

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
    private UUID permissionId;

}
