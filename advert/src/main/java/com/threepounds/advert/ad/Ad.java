package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.city.City;
import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.entity.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

  @Column(nullable = false)
  private double latitude;

  @Column(nullable = false)
  private double longitude;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.ALL})
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "country_id")
  private Country country;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_ad" ,joinColumns = @JoinColumn(name = "ad_id") , inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users = new ArrayList<>();

  @Column(nullable = false)
  private int viewCount = 0;

  public Ad() {}

  public Ad(String title, String description, BigDecimal price, int latitude, int longitude, Category category, Country country, City city, User user) {
    this.title = title;
    this.description = description;
    this.price = price;
    this.latitude = latitude;
    this.longitude = longitude;
    this.category = category;
    this.country = country;
    this.city = city;
    this.user = user;
    this.viewCount = 0;
  }

}
