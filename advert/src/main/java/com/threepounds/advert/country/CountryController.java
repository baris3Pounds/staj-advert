package com.threepounds.advert.country;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/countries")
@RestController
public class CountryController {

  private final CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  public List<Country> getCountries() {
    return countryService.list();
  }

  @PostMapping
  public Country save(@RequestBody Country country) {
    return countryService.save(country);
  }

  @GetMapping("/by-name")
  public List<Country> getCountriesByName(@RequestParam String name) {
    return countryService.listByName(name);
  }
}
