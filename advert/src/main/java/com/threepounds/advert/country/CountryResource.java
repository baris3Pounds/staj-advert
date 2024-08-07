package com.threepounds.advert.country;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryResource {
    private UUID id;
    private String name;
    private int phoneCode;
    private String isoCode3;
    private boolean active;
}
