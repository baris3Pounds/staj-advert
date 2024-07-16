package com.threepounds.advert.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.threepounds.advert.rolePermision.entity.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {

  @Id @GeneratedValue private UUID id;

  @Column private String name;
  @Column private int age;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private boolean active;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
  @JsonBackReference
  private List<Role> roles = new ArrayList<>();

  public User(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }
}
