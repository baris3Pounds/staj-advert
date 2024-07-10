package com.threepounds.advert.country;

import com.threepounds.advert.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
    public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> list(){
            return countryRepository.findAll();
        }

    public Country save(Country country){
        country.setActive(true);
        return countryRepository.save(country);
    }

    public List<Country> listByName(String name) {
        return countryRepository.findByName(name);
    }

    public List<Country> listByIso_code_3(String iso_code_3) {
        return countryRepository.findByIso_code_3(iso_code_3);
    }

    public Optional<Country> getById(UUID countryID ){
        return countryRepository.findById(countryID);
    }

    public void deleteCountry(Country country){
        countryRepository.delete(country);
    }
}
