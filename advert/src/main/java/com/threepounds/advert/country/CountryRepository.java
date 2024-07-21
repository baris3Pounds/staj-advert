package com.threepounds.advert.country;

import com.threepounds.advert.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends PagingAndSortingRepository<Country, UUID> {

  List<Country> findByIsoCode3(String isoCode3);

  List<Country> findByName(String name);
}
