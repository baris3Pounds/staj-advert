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

    public  AdController(AdService adService) {this.adService = adService;
    }

    @GetMapping
    public List<Ad> getAds() {return adService.list();}

    @GetMapping("/{adId}")
    public Ad getById(@PathVariable UUID adId){
    return adService.getById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
    }

    @PostMapping
    public void addAd(@RequestBody Ad ad) {adService.save(ad);}


   /* @PutMapping()
    public void updateAdPrice(@RequestBody Ad ad) {
        public ResponseEntity<Ad> update(@PathVariable UUID adId, @RequestBody Ad ad);
        Ad existingAd = AdService.getId(adId).orElseThrow(() -> new RuntimeException("User not found"));
        existingAd.setTitle(ad.getTitle());
        existingAd.setPrice(ad.getPrice());
        Ad updatedAds = adService.save(existingAd);}
*/

}
