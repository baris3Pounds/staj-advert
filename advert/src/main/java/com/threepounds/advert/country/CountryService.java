package com.threepounds.advert.country;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> list(int no , int size){
        PageRequest pageble = PageRequest.of(no, size);
        Page<Country> page = countryRepository.findAll(pageble);
        return page.toList();
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

    public void deleteById(Country country){
         countryRepository.delete(country);
    }
}
