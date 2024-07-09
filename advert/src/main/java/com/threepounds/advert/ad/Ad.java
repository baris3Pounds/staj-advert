package com.threepounds.advert.ad;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "ads")
public class Ad {

  @Id
  @GeneratedValue
  private UUID id;

  // title
  @Column
  private String title;

  // description
  @Column
  private String description;

  // price  (BigDecimal)
  @Column
  private BigDecimal price;

  // active (boolean)
  @Column
  private boolean active=true;

  public Ad(){}

  public Ad(String title, String description, BigDecimal price) {
    this.title = title;
    this.description = description;
    this.price =price;
  }





}
