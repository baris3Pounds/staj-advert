package com.threepounds.advert.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String name;
  @Column
  private int age;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender;

  private boolean active;

  public User(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

}



