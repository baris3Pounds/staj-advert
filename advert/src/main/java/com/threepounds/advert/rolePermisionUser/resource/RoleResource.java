package com.threepounds.advert.rolePermisionUser.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleResource {
    private UUID id;
    private String name;
    private List<PermissionResource> permissions = new ArrayList<>();
    private String code;
}
