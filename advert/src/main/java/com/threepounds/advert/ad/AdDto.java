package com.threepounds.advert.ad;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class AdDto {

  private UUID id;

  @NotEmpty(message = " name cannot be empty")
  private String title;
  @NotBlank(message = "description is mandatory")
  private String description;
  @Min(value = 0,message = "cannot be less than 0")
  private BigDecimal price;
  private UUID categoryId;
}
