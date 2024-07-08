package com.threepounds.advert.ad;

import com.threepounds.advert.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path = "/ad")
@RestController
public class AdController {


    private final AdService adService;
    private final AdRepository adsRepository;

    public  AdController(AdService adService, AdRepository adRepository) {this.adService = adService;
        this.adsRepository = adRepository;
    }

    @GetMapping
    public List<Ad> getAds() {return adService.list();}

    @PostMapping
    public void addAd(@RequestBody Ad ad) {adService.save(ad);}

    @GetMapping(path = "/by-title")
    public List<Ad> getAdsByTitle(@RequestParam String name) {return adService.listByTitle(name);}

    @PutMapping(path = "/{adId}")
    public ResponseEntity<Ad> update(@PathVariable UUID adId, @RequestBody Ad ad) {
        Ad existingAd = adService.getById(adId).orElseThrow(() -> new RuntimeException("User not found"));
        existingAd.setTitle(ad.getTitle());
        existingAd.setPrice(ad.getPrice());
        Ad updatedAds = adService.save(existingAd);
        return ResponseEntity.ok().body(updatedAds);
    }

}
