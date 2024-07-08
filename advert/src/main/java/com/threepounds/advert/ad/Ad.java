package com.threepounds.advert.ad;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

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

  public UUID getId() {return id;}

  public void setId(UUID id) {this.id = id;}

  public String getTitle() {return title;}

  public void setTitle(String title) {this.title = title;}

  public String getDescription() {return description;}

  public  void setDescription(String description) {this.description = description;}

  public BigDecimal getPrice() {return price;}

  public void setPrice(BigDecimal price) {this.price = price;}

  public boolean isActive() {return active;}

  public void setActive(boolean active) {this.active = active;}

}
