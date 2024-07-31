package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import java.awt.print.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface AdRepository extends JpaRepository<Ad, UUID>, JpaSpecificationExecutor<Ad> {
  List<Ad> findByTitle(String title);

  List<Ad> findByActive(Pageable page);

}
