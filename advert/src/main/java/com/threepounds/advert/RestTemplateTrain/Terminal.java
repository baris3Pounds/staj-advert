package com.threepounds.advert.RestTemplateTrain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Terminal {

   @Bean
    CommandLineRunner commandLineRunner(RestTemplateService restTemplateService){


       return result ->{

           Location local = restTemplateService.getLocation("24.48.0.1");
           System.out.println(local.toString());
       };
   }
}
