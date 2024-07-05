package com.threepounds.advert.ad;

import com.threepounds.advert.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AdRepository extends JpaRepository<Ad, UUID> {

    List<Ad> findByTitle(String title);
    List<Ad> findByTitleAndDescription(String title, String description);


}
