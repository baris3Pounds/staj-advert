package com.threepounds.advert.ad;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;


@Data
public class AdDto {

  private UUID id;

  // title
  private String title;

  // description
  private String description;

  // price  (BigDecimal)
  private BigDecimal price;

  private UUID categoryId;
}
