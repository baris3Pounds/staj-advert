package com.threepounds.advert.rolePermisionUser.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private List<UUID> permissionIds;

}
