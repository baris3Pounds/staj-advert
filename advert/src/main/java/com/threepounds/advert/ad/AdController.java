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
    public ResponseEntity<List<AdResource>> getAllAd(){
        List<Ad> ad = adService.findAll();
        if (ad == null || ad.isEmpty()) {
            return null;
        }
        List<AdResource> adResourcesList = adMapper.adListToAdResourceList(ad);
        return ResponseEntity.ok().body(adResourcesList);
    }

  /*@GetMapping(path = "/by-title")
public List<Ad> getAdsByTitle(@RequestParam String name) {
  return adService.listByTitle(name);
}*/

    @GetMapping(path = "/by-title")
    public ResponseEntity<List<AdResource>> getAdsByTitle(@RequestParam String title) {
        List<Ad> ad = adService.listByTitle(title);
        List<AdResource> adResourceList = adMapper.adListToAdResourceList(ad);

        return ResponseEntity.ok().body(adResourceList);
    }

  /*@PostMapping
  public ResponseEntity<AdDto> addAd(@RequestBody @Valid @NotNull AdDto adDto) {

    restTemplateService.getLocation("24.48.0.1");

    Category category = categoryService.findById(adDto.getCategoryId());
    Ad ad = adMapper.adDtoToAd(adDto);
    if(category != null){
      ad.setCategory(category);
    }

    Ad savedAd = adService.save(ad);
    AdDto resource = adMapper.adToAdDto(savedAd);
    return ResponseEntity.ok().body(resource);
  }*/

    @PostMapping
    public ResponseEntity<AdResource> createAd(@Valid @RequestBody AdDto adDto) {
        restTemplateService.getLocation("24.48.0.1");
        Ad ad = adMapper.adDtoToAd(adDto);

        Category category = categoryService.findById(adDto.getCategoryId());
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        ad.setCategory(category);
        Ad savedAd = adService.save(ad);
        AdResource adResource = adMapper.adToAdResourceList(savedAd);

        return ResponseEntity.ok().body(adResource);
    }

    /*@PutMapping(path = "/{adId}")
    public ResponseEntity<Ad> update(@PathVariable UUID adId, @RequestBody Ad ad) {
      Ad existingAd =
          adService.getById(adId).orElseThrow(() -> new RuntimeException("User not found"));
      existingAd.setTitle(ad.getTitle());
      existingAd.setDescription(ad.getDescription());
      existingAd.setPrice(ad.getPrice());
      Ad updatedAd = adService.save(existingAd);

      return ResponseEntity.ok().body(updatedAd);
    }*/
    @PutMapping(path = "/{adId}")
    public ResponseEntity<AdResource> updateAd(@PathVariable @NotNull UUID adId, @Valid @NotNull @RequestBody AdDto adDto) {
        Optional<Ad> ad = adService.getById(adId);
        ad.get().setTitle(adDto.getTitle());
        ad.get().setDescription(adDto.getDescription());
        ad.get().setPrice(adDto.getPrice());
        AdResource adResource = adMapper.adToAdResourceList(ad.get());

        return ResponseEntity.ok().body(adResource);
    }

    @PutMapping(path = "/{adId}/viewed")
    public ResponseEntity<AdResource> update(@PathVariable UUID adId) {
        Ad existingAd =
                adService.getById(adId).orElseThrow(() -> new RuntimeException("Ad not found"));
        existingAd.setViewCount(existingAd.getViewCount()+1);
        Ad updatedAd = adService.save(existingAd);
        AdResource adResource = adMapper.adToAdResourceList(updatedAd);

        return ResponseEntity.ok().body(adResource);
    }
    @DeleteMapping("/{adId}")
    public ResponseEntity<AdDto> delete(@Valid @PathVariable UUID adId) {
        Ad existingAd =
                adService.getById(adId).orElseThrow(() -> new RuntimeException("User not found"));
        adService.deleteAd(existingAd);

        return ResponseEntity.ok().build();
    }
}




