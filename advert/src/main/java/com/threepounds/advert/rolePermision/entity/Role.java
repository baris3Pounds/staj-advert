package com.threepounds.advert.rolePermision.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.threepounds.advert.user.User;
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

    private String name;

    @ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> users = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions" ,joinColumns = @JoinColumn(name = "role_id") , inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions =  new  ArrayList<>();

}
