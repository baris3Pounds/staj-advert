package com.threepounds.advert.country.city;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    City cityResourceToCity(CityResource cityResource);
    CityResource cityToCityResource(City city);
    List<CityResource> cityListToCityResourceList(List<City> cityList);

}
