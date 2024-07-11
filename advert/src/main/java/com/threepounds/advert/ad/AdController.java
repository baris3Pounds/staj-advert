package com.threepounds.advert.ad;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/ads")
@RestController
public class AdController {

  private final AdService adService;

  public AdController(AdService adService) {
    this.adService = adService;
  }

  @GetMapping
  public List<Ad> getAds() {
    return adService.list();
  }

  @PostMapping
  public void addAd(@RequestBody Ad ad) {
    adService.save(ad);
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
