package com.threepounds.advert.RestTemplateTrain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String query;
    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String city;
    private String lat;
    private String lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;


    @Override
    public String toString() {
        return "Location{" +
                "query='" + query + '\'' +
                ", status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", timezone='" + timezone + '\'' +
                ", isp='" + isp + '\'' +
                ", org='" + org + '\'' +
                ", as='" + as + '\'' +
                '}';
    }
}
