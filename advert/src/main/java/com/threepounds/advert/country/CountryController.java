package com.threepounds.advert.country;


import com.threepounds.advert.category.Category;
import com.threepounds.advert.category.CategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/countries")
@RestController

public class CountryController {

        private final CountryService countryService;
        private final CountryMapper countryMapper;

        public CountryController(CountryService countryService, CountryMapper countryMapper) {
            this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<CountryDTO>>getCountries(@PathVariable Optional<Integer> no  , @PathVariable Optional<Integer> size){
            List<Country> countries = countryService.findAll(no.orElse(0) , no.orElse(10));
            List<CountryDTO> countryDTOList = countryMapper.countryListToCountryDTOList(countries);
            return ResponseEntity.ok(countryDTOList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(UUID id){
        Country country = countryService.findById(id);
        CountryDTO countryDTO = countryMapper.countryToCountryDTO(country);
        return ResponseEntity.ok().body(countryDTO);
    }

    @PostMapping("")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO){
            Country country = countryMapper.countryDTOToCountry(countryDTO);
            Country savedCountry = countryService.save(country);
            return ResponseEntity.ok().body(countryMapper.countryToCountryDTO(savedCountry));

    }

    @GetMapping("/by-name")
    public List<CountryDTO> getCountriesByName(String name) {
      List<Country> countries = countryService.listByName(name);
      return countryMapper.countryListToCountryDTOList(countries);
    }

   @PutMapping("/{id}")
   public ResponseEntity<CountryDTO> updateCountry(@PathVariable UUID id, @RequestBody CountryDTO countryDTO){
       Country country = countryMapper.countryDTOToCountry(countryDTO);
       country.setId(id);
       Country savedCountry = countryService.save(country);
       return ResponseEntity.ok().body(countryMapper.countryToCountryDTO(savedCountry));
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<CountryDTO> deleteCountry(@PathVariable UUID id){
        Country existingCountry = countryService.getById(id).orElseThrow(() -> new RuntimeException("Country not found"));
        countryService.deleteById(existingCountry);
        return ResponseEntity.ok().body(countryMapper.countryToCountryDTO(existingCountry));
    }

    }
