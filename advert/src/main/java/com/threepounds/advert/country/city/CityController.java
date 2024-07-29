package com.threepounds.advert.country.city;

import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.CountryService;
import com.threepounds.advert.exception.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cities")

public class CityController {

    private final CityService cityService;
    private final CityMapper cityMapper;
    private final CountryService countryService;

    public CityController(CityService cityService, CityMapper cityMapper, CountryService countryService) {
        this.cityService = cityService;
        this.cityMapper = cityMapper;
        this.countryService = countryService;
    }

    @GetMapping("")
    public ResponseEntity<GeneralResponse<Object>> getAllCities(@RequestParam Optional<Integer> no , @RequestParam Optional<Integer> size) {
    List<City>cities= cityService.findAll(no.orElse(0), size.orElse(10));
    List<CityResource> citiesResources = cityMapper.cityListToCityResourceList(cities);
    return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(citiesResources).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<CityResource>> getCityById(@PathVariable UUID id) {
        City city = cityService.findById(id);
        CityResource cityResource = cityMapper.cityToCityResource(city);
        return ResponseEntity.ok().body(GeneralResponse.<CityResource>builder().data(cityResource).build());
    }

    @PostMapping("")
    public ResponseEntity<GeneralResponse<CityResource>> createCity(@RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        Country country = countryService.getById(cityDto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country Not Found"));
        city.setCountry(country);
        City savedCity = cityService.save(city);
        CityResource cityResource = cityMapper.cityToCityResource(savedCity);

        return ResponseEntity.ok().body(GeneralResponse.<CityResource>builder().data(cityResource).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<CityResource>> updateCity(@PathVariable UUID id, @RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        city.setId(id);
        City updatedCity = cityService.save(city);
        CityResource cityResource = cityMapper.cityToCityResource(updatedCity);

        return ResponseEntity.ok().body(GeneralResponse.<CityResource>builder().data(cityResource).build());
    }

    @DeleteMapping("/{id}")
    public CityResource deleteCity(@PathVariable UUID id) {
        City existingCity = cityService.findById(id);
        cityService.deleteById(id);
        return cityMapper.cityToCityResource(existingCity);
    }
}
