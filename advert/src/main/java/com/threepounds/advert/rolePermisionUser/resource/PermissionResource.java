package com.threepounds.advert.rolePermisionUser.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionResource {
    private UUID id;
    private String name;
}
