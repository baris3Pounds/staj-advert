package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import com.threepounds.advert.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdRepository extends PagingAndSortingRepository<Ad, UUID> {
  List<Ad> findByTitle(String title);
  List<Ad> findByCategory(Category category);
  List<Ad> findByTitleAndCategory(String title, Category category);
  int countAdByCategory(Category category);

}
