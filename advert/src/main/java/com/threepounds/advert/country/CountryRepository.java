package com.threepounds.advert.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

  List<Country> findByIsoCode3(String isoCode3);

  List<Country> findByName(String name);

    void delete(Country country);

  List<Country> findAll();
}
