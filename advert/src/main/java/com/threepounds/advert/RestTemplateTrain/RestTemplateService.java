package com.threepounds.advert.RestTemplateTrain;

import com.threepounds.advert.config.DistanceAd;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestTemplateService {


    private final RestTemplate restTemplate;

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;

    private final DistanceAd distanceAd;

    public Location getLocation(String ip){
       LocationDto locationDto = restTemplate.getForObject("http://ip-api.com/json/" + ip , LocationDto.class);
       Location location = locationMapper.LocationDtoToLocation(locationDto);
       return locationRepository.save(location);

    }

    private List<Location> findNearestLocations(double enlem , double boylam , double radius){
        List<Location> AllLocations = locationRepository.findAll();
        List<Location> nearestLocations = new ArrayList<>();
        for (Location location : AllLocations) {
            double mesafe = distanceAd.calculateDistance(enlem , boylam ,location.getLat() , location.getLon());
            if(mesafe <= radius){
                nearestLocations.add(location);
            }
        }
        return nearestLocations;
    }

}
