package com.threepounds.advert.country;

import com.threepounds.advert.category.Category;
import com.threepounds.advert.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
    public abstract class CountryService {

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

    public List<Country> listByIsoCode3(String isocode3) {
        return countryRepository.findByIsoCode3(isocode3);
    }

    public Optional<Country> getById(UUID countryID ){
        return countryRepository.findById(countryID);
    }


    public Country findById(UUID id) {
        return countryRepository.findById(id).get();
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public abstract void deleteById(Country country);
}
