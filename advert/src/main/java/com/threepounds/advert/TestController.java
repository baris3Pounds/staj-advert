package com.threepounds.advert;


import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/users")
  List<User> listUsers(){
    List<User> users = Arrays.asList(new User("Baris",33,Gender.MALE),
        new User("Ahmet",28,Gender.MALE),
        new User("Ayse",24,Gender.FEMALE));

    return users;
  }

}
