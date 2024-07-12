package com.threepounds.advert.rolePermision;

import com.threepounds.advert.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    // Karısıklık olmasın diye ayrı pakete yazdım
    //Testing

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permissions" ,joinColumns = @JoinColumn(name = "role_id") , inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions =  new  ArrayList<>();

}
