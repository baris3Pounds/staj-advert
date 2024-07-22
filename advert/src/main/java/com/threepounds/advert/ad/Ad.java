package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ads")
public class Ad {

  @Id @GeneratedValue private UUID id;

  @Column private String title;

  // description
  @Column private String description;

  // price  (BigDecimal)
  @Column private BigDecimal price;

  // active (boolean)
  @Column private boolean active = true;

  @OneToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH} , fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

  public Ad() {}

  public Ad(String title, String description, BigDecimal price) {
    this.title = title;
    this.description = description;
    this.price = price;
  }
}
