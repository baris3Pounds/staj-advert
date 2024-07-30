package com.threepounds.advert.RestTemplateTrain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateService {


    private final RestTemplate restTemplate;

    private final LocationMapper locationMapper;

    private final LocationRepository locationRepository;

    public Location getLocation(String ip){
       LocationDto locationDto = restTemplate.getForObject("http://ip-api.com/json/" + ip , LocationDto.class);
       Location location = locationMapper.LocationDtoToLocation(locationDto);
       return locationRepository.save(location);

    }

}
