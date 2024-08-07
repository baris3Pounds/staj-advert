package com.threepounds.advert.rolePermisionUser.entity;

import com.threepounds.advert.ad.Ad;
import com.threepounds.advert.country.city.City;
import com.threepounds.advert.rolePermisionUser.utils.enums.Gender;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {

  @Id @GeneratedValue private UUID id;


  @Column private String name;

  @Column private String username;

  @Column private String password;

  @Column private int age;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column
  private boolean active;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles = new ArrayList<>();

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<Ad> ads = new ArrayList<>();

  public User(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }
}
