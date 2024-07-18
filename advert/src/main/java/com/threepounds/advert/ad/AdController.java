package com.threepounds.advert.ad;

import com.threepounds.advert.category.Category;
import com.threepounds.advert.category.CategoryService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/ads")
@RestController
public class AdController {

  private final AdService adService;

  private final AdMapper adMapper;

  private final CategoryService categoryService;

  public AdController(AdService adService, AdMapper adMapper, CategoryService categoryService) {
    this.adService = adService;
    this.adMapper = adMapper;
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Ad> getAds() {
    return adService.list();
  }

  @PostMapping
  public ResponseEntity<AdDto> addAd(@RequestBody AdDto adDto) {
    Category category = categoryService.findById(adDto.getCategoryId());
    Ad ad = adMapper.adToAdDto(adDto);
    ad.setCategory(category);
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
}
