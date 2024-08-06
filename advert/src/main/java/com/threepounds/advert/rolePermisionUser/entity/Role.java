package com.threepounds.advert.rolePermisionUser.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String code;

    @ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions" ,joinColumns = @JoinColumn(name = "role_id") , inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions =  new  ArrayList<>();

    @PrePersist
    private void prePersist() {
        if (name == null || name.isEmpty()) {
            name = "Default";
        }
        if (code == null || code.isEmpty()) {
            code = "DEFAULT_CODE";
        }
    }
}