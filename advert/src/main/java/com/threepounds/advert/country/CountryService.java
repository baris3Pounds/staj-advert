package com.threepounds.advert.country;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

  @Autowired private CountryRepository countryRepository;

  public List<Country> list() {
    return countryRepository.findAll();
  }

  public Country save(Country country) {
    country.setActive(true);
    return countryRepository.save(country);
  }

  public List<Country> listByName(String name) {
    return countryRepository.findByName(name);
  }

  public List<Country> listByIsoCode3(String isoCode3) {
    return countryRepository.findByIsoCode3(isoCode3);
  }
}
