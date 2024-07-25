package com.threepounds.advert.rolePermisionUser.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionDto {
    private UUID id;
    @NotBlank
    private String name;
}
