package com.threepounds.advert.ad;

import com.threepounds.advert.RestTemplateTrain.RestTemplateService;
import com.threepounds.advert.annotations.LogExecutionTime;
import com.threepounds.advert.category.Category;
import com.threepounds.advert.category.CategoryService;
import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.CountryService;
import com.threepounds.advert.country.city.City;
import com.threepounds.advert.country.city.CityService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/ads")
@RestController
public class AdController {

  private final AdService adService;

  private final AdMapper adMapper;

  private final CategoryService categoryService;
  private final CountryService countryService;
  private final CityService cityService;

  private final RestTemplateService restTemplateService;

  
  public AdController(AdService adService, AdMapper adMapper, CategoryService categoryService,
      RestTemplateService restTemplateService,CountryService countryService, CityService cityService) {
    this.adService = adService;
    this.adMapper = adMapper;
    this.categoryService = categoryService;
    this.countryService = countryService;
    this.cityService = cityService;
    this.restTemplateService = restTemplateService;
  }

  @LogExecutionTime
  @GetMapping
  public List<Ad> getAds(@RequestParam Optional<Integer> no , @RequestParam Optional<Integer> size)
      throws InterruptedException {
    Thread.sleep(500);
    return adService.list(no.orElse(0), size.orElse(10));
  }

  @PostMapping
  public ResponseEntity<AdDto> addAd(@Valid @RequestBody AdDto adDto) {

    restTemplateService.getLocation("24.48.0.1");

    Category category = categoryService.findById(adDto.getCategoryId());
    Ad ad = adMapper.adToAdDto(adDto);
    if(category != null){
      ad.setCategory(category);
      Country country = countryService.getById(adDto.getCountryId())
              .orElseThrow(() -> new RuntimeException("Country Not Found"));
      ad.setCountry(country);
      City city = cityService.getById(adDto.getCityId())
              .orElseThrow(() -> new RuntimeException("Country Not Found"));
      ad.setCity(city);
    }

    Ad savedAd = adService.save(ad);
    AdDto resource = adMapper.adToAdDto(savedAd);
    return ResponseEntity.ok().body(resource);
  }


  @GetMapping(path = "/by-title")
  public List<Ad> getAdsByTitle(@RequestParam String name) {
    return adService.listByTitle(name);
  }


  @PutMapping(path = "/{adId}")
  public ResponseEntity<Ad> update(@PathVariable UUID adId, @RequestBody Ad ad) {
    Ad existingAd =
        adService.getById(adId).orElseThrow(() -> new RuntimeException("User not found"));
    existingAd.setTitle(ad.getTitle());
    existingAd.setDescription(ad.getDescription());
    existingAd.setPrice(ad.getPrice());
    Ad updatedAd = adService.save(existingAd);

    return ResponseEntity.ok().body(updatedAd);
  }


  @DeleteMapping("/{adId}")
  public ResponseEntity delete(@PathVariable UUID adId) {
    Ad existingAd =
        adService.getById(adId).orElseThrow(() -> new RuntimeException("User not found"));
    adService.deleteAd(existingAd);

    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/{adId}/viewed")
  public ResponseEntity<Ad> update(@PathVariable UUID adId) {
    Ad existingAd =
        adService.getById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
    existingAd.setViewCount(existingAd.getViewCount()+1);
    Ad updatedAd = adService.save(existingAd);

    return ResponseEntity.ok().body(updatedAd);
  }

}
