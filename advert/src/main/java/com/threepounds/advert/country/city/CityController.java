package com.threepounds.advert.country.city;

import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.CountryService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cities")

public class CityController {

   private final CityService cityService;
   private final CityMapper cityMapper;

   private final CountryService countryService;

    public CityController(CityService cityService, CityMapper cityMapper,
        CountryService countryService) {
       this.cityService = cityService;
       this.cityMapper = cityMapper;
      this.countryService = countryService;
    }

    @GetMapping("")
    public ResponseEntity<List<CityDto>>getAllCities(@RequestParam Optional<Integer> no , @RequestParam Optional<Integer> size) {
    List<City>cities= cityService.findAll(no.orElse(0), size.orElse(10));
    List<CityDto> cityDtoList =cityMapper.cityListtoCityDtoList(cities);
    return ResponseEntity.ok(cityDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable UUID id) {
        City city = cityService.findById(id);
        CityDto cityDto = cityMapper.citytoCityDto(city);
        return ResponseEntity.ok(cityDto);
    }

    @PostMapping("")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtotoCity(cityDto);
    Country country =
        countryService.getById(cityDto.getCountryId()).orElseThrow(() -> new RuntimeException("Country Not found"));
    city.setCountry(country);
    City savedCity = cityService.save(city);
        return ResponseEntity.ok(cityMapper.citytoCityDto(savedCity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable UUID id, @RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtotoCity(cityDto);
        city.setId(id);
        City savedCity = cityService.save(city);
        return ResponseEntity.ok(cityMapper.citytoCityDto(savedCity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CityDto> deleteCity(@PathVariable UUID id) {
        City existingCity = cityService.findById(id);
        cityService.deleteById(existingCity);
        return ResponseEntity.ok(cityMapper.citytoCityDto(existingCity));
    }
}
