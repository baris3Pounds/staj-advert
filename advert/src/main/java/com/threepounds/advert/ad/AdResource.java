package com.threepounds.advert.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdResource {
    private String title;
    private String description;
    private BigDecimal price;
    private List<AdResource> adResourceList = new ArrayList<>();

    private UUID categoryId;
    private UUID countryId;
    private UUID cityId;
}