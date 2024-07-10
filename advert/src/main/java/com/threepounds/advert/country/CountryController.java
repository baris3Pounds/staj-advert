package com.threepounds.advert.country;


import com.threepounds.advert.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

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

    @PutMapping("/{countyrId}")
    public <id> ResponseEntity<Country> update(@PathVariable UUID countyrId, @RequestBody Country country) {
        Country existingCountry =
                countryService.getById(countyrId).orElseThrow(() -> new RuntimeException("Country not found"));
        existingCountry.setPhone_code(country.getPhone_code());
        existingCountry.setName(country.getName());
        existingCountry.setIso_code_3(country.getIso_code_3());
        Country updateCountry = countryService.save(existingCountry);
        return ResponseEntity.ok().body(updateCountry);
    }

    // DeleteMapping
    @DeleteMapping("/{countryId}")
    public ResponseEntity delete(@PathVariable UUID countryId) {
        Country existingUser =
                countryService.getById(countryId).orElseThrow(() -> new RuntimeException("Country not found"));
        countryService.deleteCountry(existingUser);
        return ResponseEntity.ok().build();
    }
    }
