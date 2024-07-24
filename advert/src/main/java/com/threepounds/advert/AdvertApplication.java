package com.threepounds.advert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AdvertApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdvertApplication.class, args);
  }
}
