package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

  public List<Ad> list(int no , int size) {

    PageRequest pageble = PageRequest.of(no , size);
    Page<Ad> myList = adRepository.findAll(pageble);

    return myList.toList();
  }

  public List<Ad> listByTitle(String title) {
    return adRepository.findByTitle(title);
  }

  public Ad getById(UUID adId) {return adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found."));}

  public void deleteAd(Ad ad) { ad.setActive(false); adRepository.save(ad); }

  public List<Ad> findAll() {  return adRepository.findAll(); }


  public List<Ad> search(AdSearchModel searchModel) {
    Specification<Ad> spec = Specification.where(AdSpecifications.isActive());

    if(searchModel.getPrice()!=null){
      spec.and(AdSpecifications.priceGreaterThan(searchModel.getPrice()));
    }


    return adRepository.findAll(Specification.where(spec));
  }


}
