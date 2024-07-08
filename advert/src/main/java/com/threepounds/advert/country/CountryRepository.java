package com.threepounds.advert.country;

import com.threepounds.advert.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

    List<Country> findByIso_code_3(String iso_code_3);

    List<Country> findByName(String name);
}
