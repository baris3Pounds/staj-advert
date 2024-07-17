package com.threepounds.advert.rolePermision.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionResource {
    private UUID id;
    private String name;
    private List<RoleResource> roles = new ArrayList<>();
}
