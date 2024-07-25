package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
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

  @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Category category;

  public Ad() {}

  public Ad(String title, String description, BigDecimal price) {
    this.title = title;
    this.description = description;
    this.price = price;
  }

}
