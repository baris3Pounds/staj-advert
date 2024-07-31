package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.city.City;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ads")
public class Ad {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(length = 500)
  private String description;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private boolean active = true;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.ALL})
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "country_id")
  private Country country;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "city_id")
  private City city;

  @Column(nullable = false)
  private int viewCount = 0;

  // latitude
  // longitude
  // user one to many

  public Ad() {}

  public Ad(String title, String description, BigDecimal price) {
    this.title = title;
    this.description = description;
    this.price = price;
  }

}
