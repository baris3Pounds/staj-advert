package com.threepounds.advert.country.city;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CityDto {
  private String name;
  private UUID countryId;
}
