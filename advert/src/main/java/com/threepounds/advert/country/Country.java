package com.threepounds.advert.country;

import com.threepounds.advert.ad.Ad;
import com.threepounds.advert.country.city.City;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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


  // cities one to many
  @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
  private List<City> cities = new ArrayList<>();

  @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
  private List<Ad> ads = new ArrayList<>();

}
