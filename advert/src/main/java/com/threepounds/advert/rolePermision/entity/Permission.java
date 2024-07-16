package com.threepounds.advert.rolePermision.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String name;

    @ManyToMany(mappedBy = "permissions" , fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Role> roles = new ArrayList<>();
}
