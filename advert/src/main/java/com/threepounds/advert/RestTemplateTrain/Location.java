package com.threepounds.advert.RestTemplateTrain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue
    private UUID id;

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
    private String asDescription;

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
                ", asDescription='" + asDescription + '\'' +
                '}';
    }
}
