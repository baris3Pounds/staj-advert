package com.threepounds.advert.country.city;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDto citytoCityDto(City city);
    City cityDtotoCity(CityDto cityDto);
    List<CityDto> cityListtoCityDtoList(List<City> cityList);
    List<City> cityDtoListtoCityList(List<CityDto> cityDtoList);

}
