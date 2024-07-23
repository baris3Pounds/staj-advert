package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdService {

  private AdRepository adRepository;

  public AdService(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  public Ad save(Ad ad) {
    ad.setActive(true);
    return adRepository.save(ad);
  }

  public List<Ad> list() {
    Sort sort = Sort.by(Sort.Direction.ASC, "id");
    PageRequest pageble = PageRequest.of(1, 10, sort);
    Page<Ad> myList = adRepository.findAll(pageble);

    return myList.toList();
  }

  public List<Ad> listByTitle(String title) {
    return adRepository.findByTitle(title);
  }

  public Optional<Ad> getById(UUID adId) {
    return adRepository.findById(adId);
  }

  public void deleteAd(Ad ad) {
    adRepository.delete(ad);
  }


  public List<Ad> listByCategory(Category category) {
    return adRepository.findByCategory(category);
  }

}
