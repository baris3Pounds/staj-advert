package com.threepounds.advert.rolePermisionUser.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "permissions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permission {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String code;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions" ,joinColumns = @JoinColumn(name = "permission_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
