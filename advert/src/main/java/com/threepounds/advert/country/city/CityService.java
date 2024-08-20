package com.threepounds.advert.country.city;

import com.threepounds.advert.country.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Cacheable("city")
    public List<City> findAll(int no , int size) {
        PageRequest pageble = PageRequest.of(no, size);
        Page<City> page = cityRepository.findAll(pageble);
        return page.toList();
    }

    public City findById(UUID id) {
        return cityRepository.findById(id).get();
    }

    @CacheEvict("city")
    public City save(City city) {
        return cityRepository.save(city);
    }

    @CacheEvict("city")
    public void deleteById(UUID existingCity) {
    }
    @Cacheable( value = "city" , key = "#cityId")
    public Optional<City> getById(UUID cityId) {
        return cityRepository.findById(cityId);
    }
}
