package com.threepounds.advert.country;

import com.threepounds.advert.user.Gender;
import jakarta.persistence.*;

import java.util.UUID;
import lombok.Data;

@Data
@Entity(name = "countries")
public class Country {

  @Id @GeneratedValue private UUID id;

  @Column private String name;
  @Column private int phoneCode;
  @Column private String isoCode3;
  @Column private boolean active;


}
