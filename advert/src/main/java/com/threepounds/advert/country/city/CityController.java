package com.threepounds.advert.country.city;

import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<CityResource> getAllCities() {
        List<City> cities = cityService.findAll();
        return cityMapper.cityListToCityResourceList(cities);
    }

    @GetMapping("/{id}")
    public CityResource getCityById(@PathVariable UUID id) {
        City city = cityService.findById(id);
        return cityMapper.cityToCityResource(city);
    }

    @PostMapping("")
    public CityResource createCity(@RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        Country country = countryService.getById(cityDto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country Not Found"));
        city.setCountry(country);
        City savedCity = cityService.save(city);
        return cityMapper.cityToCityResource(savedCity);
    }

    @PutMapping("/{id}")
    public CityResource updateCity(@PathVariable UUID id, @RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        city.setId(id);
        City updatedCity = cityService.save(city);
        return cityMapper.cityToCityResource(updatedCity);
    }

    @DeleteMapping("/{id}")
    public CityResource deleteCity(@PathVariable UUID id) {
        City existingCity = cityService.findById(id);
        cityService.deleteById(id);
        return cityMapper.cityToCityResource(existingCity);
    }
}

