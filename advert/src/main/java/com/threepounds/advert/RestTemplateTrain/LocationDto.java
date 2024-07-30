package com.threepounds.advert.RestTemplateTrain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocationDto {

    private String query;
    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String city;
    private Double lat;
    private Double lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
}
