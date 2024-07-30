package com.threepounds.advert.country;


import com.threepounds.advert.exception.GeneralResponse;
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
    public ResponseEntity<GeneralResponse<Object>>getCountries(@PathVariable Optional<Integer> no  , @PathVariable Optional<Integer> size){
            List<Country> countries = countryService.findAll(no.orElse(0) , no.orElse(10));
            List<CountryResource> countryResourceList = countryMapper.countryListToCountryResourceList(countries);
            return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(countryResourceList).build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<Object>> getCountryById(UUID id){
        Country country = countryService.findById(id);
        CountryResource countryResource = countryMapper.countryToCountryResource(country);
        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(countryResource).build());
    }

    @PostMapping("")
    public ResponseEntity<GeneralResponse<Object>> createCountry(@RequestBody CountryDTO countryDTO){
            Country country = countryMapper.countryDTOToCountry(countryDTO);
            Country savedCountry = countryService.save(country);
            CountryResource countryResource = countryMapper.countryToCountryResource(savedCountry);
            return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(countryResource).build());

    }

    @GetMapping("/by-name")
    public ResponseEntity<GeneralResponse<Object>> getCountriesByName(String name) {
      List<Country> countries = countryService.listByName(name);
      List<CountryResource> countryResourceList = countryMapper.countryListToCountryResourceList(countries);
      return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(countryResourceList).build());
    }

   @PutMapping("/{id}")
   public ResponseEntity<GeneralResponse<Object>> updateCountry(@PathVariable UUID id, @RequestBody CountryDTO countryDTO){
       Country country = countryMapper.countryDTOToCountry(countryDTO);
       country.setId(id);
       Country savedCountry = countryService.save(country);
       CountryResource countryResource = countryMapper.countryToCountryResource(savedCountry);
       return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(countryResource).build());
   }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<Object>> deleteCountry(@PathVariable UUID id){
        Country existingCountry = countryService.getById(id).orElseThrow(() -> new RuntimeException("Country not found"));
        countryService.deleteById(existingCountry);
        CountryResource countryResource = countryMapper.countryToCountryResource(existingCountry);
        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(countryResource).build());
    }

}
