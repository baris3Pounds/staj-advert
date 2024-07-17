package com.threepounds.advert.country.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findAll();

    Optional<City> findById(UUID id);

    @SuppressWarnings("unchecked")
    City save(City city);
}
