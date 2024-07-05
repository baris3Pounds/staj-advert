package com.threepounds.advert.country;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity(name = "countries")
public class Country {

  @Id
  @GeneratedValue
  private UUID id;

  // name
  // phone_code
  // iso_code_3
  // active (boolean)
}
