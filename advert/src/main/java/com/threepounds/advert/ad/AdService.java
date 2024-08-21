package com.threepounds.advert.ad;
import com.threepounds.advert.config.DistanceAd;
import com.threepounds.advert.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdService {

  private AdRepository adRepository;
  private final DistanceAd distanceAd;
  private final RabbitTemplate rabbitTemplate;

  public AdService(AdRepository adRepository, DistanceAd distanceAd , RabbitTemplate rabbitTemplate) {
    this.adRepository = adRepository;
      this.distanceAd = distanceAd;
      this.rabbitTemplate = rabbitTemplate;

  }

  public Ad save(Ad ad) {
    ad.setActive(true);
    return adRepository.save(ad);
  }


  @CachePut(value = "ads", key = "#id")
  public Ad update(Ad ad, UUID id) {
    ad.setId(id);
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

  @Cacheable(value = "ads",key = "#adId")
  public Ad getById(UUID adId) {return adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Ad not found."));}

  public void deleteAd(Ad ad) {
    ad.setActive(false);
    adRepository.save(ad);
    rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ADVERT_DELETE_ROUTING_KEY, ad.getId());
    System.out.println("Message sent for advert id: " + ad.getId());}

  public List<Ad> findAll() {  return adRepository.findAll(); }


  public List<Ad> search(AdSearchModel searchModel) {
    Specification<Ad> spec = Specification.where(AdSpecifications.isActive());

    if(searchModel.getPrice()!=null){
      spec.and(AdSpecifications.priceGreaterThan(searchModel.getPrice()));
    }


    return adRepository.findAll(Specification.where(spec));
  }

  public List<Ad> nearestLocations(AdDistanceDto adDistanceDto) {
    List<Ad> allAds = adRepository.findAll();
    List<Ad> nearestAds = new ArrayList<>();
    for (Ad ad : allAds) {
      double mesafe = distanceAd.calculateDistance(adDistanceDto.getEnlem() , adDistanceDto.getBoylam(), ad.getLatitude() , ad.getLongitude());
      if(mesafe <= adDistanceDto.getRadius()){
        nearestAds.add(ad);
      }
    }
    return nearestAds;
  }


}
