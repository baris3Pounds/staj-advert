package com.threepounds.advert.ad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdResource {
    private String title;
    private String description;
    private BigDecimal price;
    private int latitude;
    private int longitude;
    private boolean isFavorite;
    private List<AdResource> adResourceList = new ArrayList<>();

}