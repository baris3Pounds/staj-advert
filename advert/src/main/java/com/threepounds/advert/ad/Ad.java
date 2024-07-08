package com.threepounds.advert.ad;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity(name = "ads")
public class Ad {

  @Id
  @GeneratedValue
  private UUID id;

  // title
  // description
  // price  (BigDecimal)
  // active (boolean)


}
