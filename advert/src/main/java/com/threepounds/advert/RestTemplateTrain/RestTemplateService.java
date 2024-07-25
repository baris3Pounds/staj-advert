package com.threepounds.advert.RestTemplateTrain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {


    private final RestTemplate restTemplate;

    public Location getLocation(String ip){
       return restTemplate.getForObject("http://ip-api.com/json/" + ip , Location.class);
    }

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
