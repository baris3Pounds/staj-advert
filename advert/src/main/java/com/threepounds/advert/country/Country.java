package com.threepounds.advert.country;


import com.threepounds.advert.user.Gender;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "countries")
public class Country {

  @Id
  @GeneratedValue
  private UUID id;

  @Column
  private String name;
  @Column
  private int phone_code;
  @Column
  private String iso_code_3;
  @Column
  private boolean active;

  public Country() {}

  public Country(String name, int phone_code,String iso_code_3 , boolean active
                 ) {
    this.name = name;
    this.phone_code = phone_code;
    this.iso_code_3 = iso_code_3;
    this.active = active;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPhone_code() {
    return phone_code;
  }

  public void setPhone_code(int phone_code) {
    this.phone_code = phone_code;
  }

  public String getIso_code_3() {
    return iso_code_3;
  }

  public void setIso_code_3(String iso_code_3) {
    this.iso_code_3 = iso_code_3;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}

