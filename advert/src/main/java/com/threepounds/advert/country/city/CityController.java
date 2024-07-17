package com.threepounds.advert.country.city;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cities")

public class CityController {

   private final CityService cityService;
   private final CityMapper cityMapper;

    public CityController(CityService cityService, CityMapper cityMapper) {
       this.cityService = cityService;
       this.cityMapper = cityMapper;
    }

    @GetMapping
    public ResponseEntity<List<CityDto>>getAllCities() {
    List<City>cities= cityService.findAll();
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
