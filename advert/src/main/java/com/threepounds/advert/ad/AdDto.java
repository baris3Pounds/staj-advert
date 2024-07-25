package com.threepounds.advert.ad;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;


@Data
public class AdDto {

  private UUID id;

  @NotBlank(message = "title is mandatory")
  private String title;

  // description
  private String description;

  // price  (BigDecimal)
  private BigDecimal price;

  private UUID categoryId;
}
