package com.threepounds.advert.country.city;

import com.threepounds.advert.ad.Ad;
import com.threepounds.advert.country.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "cities")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column
  private String name;

  // MANY TO ONE
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "country_id")
  private Country country;

  @OneToMany(mappedBy = "ad", fetch = FetchType.EAGER)
  private List<City> ads = new ArrayList<>();

}
