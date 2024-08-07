package com.threepounds.advert.ad;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

  private UUID id;

  @NotBlank(message = " name cannot be empty")
  private String title;
  @NotBlank(message = "description is mandatory")
  private String description;
  @Min(value = 0,message = "cannot be less than 0")
  private BigDecimal price;

  private UUID categoryId;
  private UUID countryId;
  private UUID cityId;
  private UUID userId;
  private double latitude;
  private double longitude;
}
