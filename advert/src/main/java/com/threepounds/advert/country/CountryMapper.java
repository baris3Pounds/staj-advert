package com.threepounds.advert.country;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO countryToCountryDTO(Country country);
    Country countryDTOToCountry(CountryDTO countryDTO);
    List<CountryDTO> countryListToCountryDTOList(List<Country> countryList);
    List<Country> countryDTOListToCountryList(List<CountryDTO> countryDTOList);
    List<CountryResource> countryListToCountryResourceList(List<Country> countries);
    CountryResource countryToCountryResource(Country country);
}
