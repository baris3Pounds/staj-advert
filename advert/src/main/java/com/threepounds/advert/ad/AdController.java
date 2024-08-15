package com.threepounds.advert.ad;
import com.threepounds.advert.RestTemplateTrain.RestTemplateService;
import com.threepounds.advert.annotations.LogExecutionTime;
import com.threepounds.advert.category.Category;
import com.threepounds.advert.category.CategoryService;
import com.threepounds.advert.country.Country;
import com.threepounds.advert.country.CountryService;
import com.threepounds.advert.country.city.City;
import com.threepounds.advert.country.city.CityService;
import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.service.UserService;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/ads")
@RestController
public class AdController {

    private final AdService adService;
    private final AdMapper adMapper;
    private final CategoryService categoryService;
    private final RestTemplateService restTemplateService;
    private final CountryService countryService;
    private final CityService cityService;
    private final UserService userService;

    public AdController(AdService adService, AdMapper adMapper, CategoryService categoryService,
                        RestTemplateService restTemplateService, UserService userService, CountryService countryService, CityService
                        cityService) {
        this.adService = adService;
        this.adMapper = adMapper;
        this.categoryService = categoryService;
        this.restTemplateService = restTemplateService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @LogExecutionTime
    @GetMapping
    public ResponseEntity<GeneralResponse<Object>> getAllAd(){
        List<Ad> ad = adService.findAll();
        List<AdResource> adResourcesList = adMapper.adListToAdResourceList(ad);
        return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(adResourcesList).build());
    }

    @GetMapping(path = "/{adId}")
    public ResponseEntity<GeneralResponse<Object>> getAdsByTitle(@RequestParam UUID adId) {
        Ad ad = adService.getById(adId);
        AdResource adResource = adMapper.adToAdResource(ad);
        return ResponseEntity.ok().body(GeneralResponse.builder().data(adResource).build());
    }
    //Test edilmedi Postmanden 403Forbidden hattası alıyorum!
    @GetMapping(path = "/by-id")
    public ResponseEntity<GeneralResponse<Object>> getById(@RequestParam UUID uuid, Principal principal) {
        User user = userService.getByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        Ad ad = adService.getById(uuid);
        AdResource adResource = adMapper.adToAdResource(ad);

        if (user.getFavouriteAds() != null && user.getFavouriteAds().contains(ad)) {
            adResource.setFavorite(true);
        } else {
            adResource.setFavorite(false);
        }

        return ResponseEntity.ok().body(GeneralResponse.builder().data(adResource).build());
    }


    @PostMapping
    public ResponseEntity<GeneralResponse<AdResource>> createAd(Principal principal, Authentication authentication, @Valid @RequestBody AdDto adDto)
        throws InterruptedException {

        restTemplateService.getLocation("24.48.0.1");
        Ad ad = adMapper.adDtoToAd(adDto);

        Category category = categoryService.findById(adDto.getCategoryId());
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        ad.setCategory(category);

        Country country = countryService.getById(adDto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country Not Found"));
        ad.setCountry(country);

        City city = cityService.getById(adDto.getCityId())
                .orElseThrow(() -> new RuntimeException("City Not Found"));
        ad.setCity(city);

        User user = userService.getByUsername(principal.getName())
            .orElseThrow(() -> new RuntimeException("User Not Found"));
        ad.setUser(user);

        Ad savedAd = adService.save(ad);
        AdResource adResource = adMapper.adToAdResource(savedAd);

        return ResponseEntity.ok().body(GeneralResponse.<AdResource>builder().data(adResource).build());
    }

    @PutMapping(path = "/{adId}")
    public ResponseEntity<GeneralResponse<AdResource>> updateAd(@PathVariable @NotNull UUID adId, @Valid @NotNull @RequestBody AdDto adDto) {
        adService.getById(adId);

        Ad updatedAd = adMapper.adDtoToAd(adDto);
        updatedAd.setId(adId);

        Ad savedAd = adService.save(updatedAd);
        AdResource adResource = adMapper.adToAdResource(savedAd);

        return ResponseEntity.ok().body(GeneralResponse.<AdResource>builder().data(adResource).build());
    }

    @PutMapping(path = "/{adId}/viewed")
    public ResponseEntity<GeneralResponse<AdResource>> update(@PathVariable UUID adId) {
        Ad existingAd =
                adService.getById(adId);
        existingAd.setViewCount(existingAd.getViewCount()+1);
        Ad updatedAd = adService.save(existingAd);
        AdResource adResource = adMapper.adToAdResource(updatedAd);

        return ResponseEntity.ok().body(GeneralResponse.<AdResource>builder().data(adResource).build());
    }

    @PutMapping(path ="/{adId}/favorite")
            public ResponseEntity<GeneralResponse<Boolean>> updateFavoriteAds(@PathVariable UUID adId, Principal principal) {
        User user = userService.getByUsername(principal.getName()) .orElseThrow(() -> new RuntimeException("User Not Found"));;
        Ad ad = adService.getById(adId);

        if (!user.getFavouriteAds().contains(ad)) {
            user.getFavouriteAds().add(ad);
            userService.save(user);
        }
        return ResponseEntity.ok().body(GeneralResponse.<Boolean>builder().data(Boolean.TRUE).build());
    }


    @DeleteMapping("/{adId}/favorite")
    public ResponseEntity<GeneralResponse<AdResource>> favoriteAds(@PathVariable UUID adId, Principal principal) {
        User user = userService.getByUsername(principal.getName()) .orElseThrow(() -> new RuntimeException("User Not Found"));;
        Ad ad = adService.getById(adId);
        user.getFavouriteAds().remove(ad);
        userService.save(user);

        return ResponseEntity.ok().body(GeneralResponse.<AdResource>builder().build());
    }

    @DeleteMapping("/{adId}")
    public ResponseEntity<GeneralResponse<AdResource>> delete(@Valid @PathVariable UUID adId) {
        Ad existingAd =
                adService.getById(adId);
        adService.deleteAd(existingAd);

        return ResponseEntity.ok().body(GeneralResponse.<AdResource>builder().build());
    }

    @PostMapping("/search")
    public ResponseEntity<List<Ad>> searchAd(AdSearchModel adSearchModel){
        return ResponseEntity.ok( adService.search(adSearchModel));
    }

    @GetMapping("/nearest")
    public ResponseEntity<GeneralResponse<List<AdResource>>> nearestAd(AdDistanceDto adDistanceDto){
       var adList = adMapper.adListToAdResourceList(adService.nearestLocations(adDistanceDto));
       return ResponseEntity.ok().body(GeneralResponse.<List<AdResource>>builder().data(adList).build());
    }
}




