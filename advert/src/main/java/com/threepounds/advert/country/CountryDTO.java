package com.threepounds.advert.country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CountryDTO {

    private UUID id;
    private String name;
    private int phoneCode;
    private String isoCode3;
    private boolean active;
}
