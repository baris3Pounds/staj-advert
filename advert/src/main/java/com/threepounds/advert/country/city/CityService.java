package com.threepounds.advert.country.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll(int no , int size) {
        PageRequest pageble = PageRequest.of(no, size);
        Page<City> page =  cityRepository.findAll(pageble);
        return page.toList();
    }

    public City findById(UUID id) {
        return cityRepository.findById(id).get();
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public void deleteById(City existingCity) {
    }
}
