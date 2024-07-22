package com.threepounds.advert.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdRepository extends JpaRepository<Ad, UUID> {
  List<Ad> findByTitle(String title);
 // List<Ad> findByCategory(Category category);
  //List<Ad> findByTitleAndCategory(String title, Category category);
  //int countAdByCategory(Category category);

}
