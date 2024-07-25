package com.threepounds.advert.ad;

import com.threepounds.advert.RestTemplateTrain.RestTemplateService;
import com.threepounds.advert.annotations.LogExecutionTime;
import com.threepounds.advert.category.Category;
import com.threepounds.advert.category.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping(path = "/api/v1/ads")
@RestController
public class AdController {

  private final AdService adService;

  private final AdMapper adMapper;

  private final CategoryService categoryService;

  private final RestTemplateService restTemplateService;

  public AdController(AdService adService, AdMapper adMapper, CategoryService categoryService,
      RestTemplateService restTemplateService) {
    this.adService = adService;
    this.adMapper = adMapper;
    this.categoryService = categoryService;
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
  public ResponseEntity<AdDto> addAd(@RequestBody @Valid @NotNull AdDto adDto) {
  public ResponseEntity<AdDto> addAd(@Valid @RequestBody AdDto adDto) {

    restTemplateService.getLocation("24.48.0.1");

    Category category = categoryService.findById(adDto.getCategoryId());
    Ad ad = adMapper.adToAdDto(adDto);
    if(category != null){
      ad.setCategory(category);
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
  public ResponseEntity<AdDto> delete(@Valid @PathVariable UUID adId) {
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
