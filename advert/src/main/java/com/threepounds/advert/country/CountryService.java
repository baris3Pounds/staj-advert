package com.threepounds.advert.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public List<Country> listByIso_code_3(String iso_code_3) {
    return countryRepository.findByIso_code_3(iso_code_3);
  }
}
