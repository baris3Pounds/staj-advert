package com.threepounds.advert.ad;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {

  private UUID id;

  @NotNull
  @NotBlank(message = " name cannot be empty")
  private String title;
  @NotBlank(message = "description is mandatory")
  private String description;
  @Min(value = 0,message = "cannot be less than 0")
  private BigDecimal price;
  @NotNull
  private UUID categoryId;
  @NotNull
  private UUID countryId;
  @NotNull
  private UUID cityId;
  private double latitude;
  private double longitude;
}
