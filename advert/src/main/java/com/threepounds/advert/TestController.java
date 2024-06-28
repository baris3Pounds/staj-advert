package com.threepounds.advert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/users")
  List<User> listUsers() {
    List<User> users =
        Arrays.asList(
            new User("Baris", 33, Gender.MALE),
            new User("Ahmet", 28, Gender.MALE),
            new User("Ayse", 24, Gender.FEMALE));
    return users;
  }

  @PostMapping("/add-users")
  List<User> addUser(@RequestBody User user) {
    List<User> users = new ArrayList<>();
    users.add(new User("Baris", 33, Gender.MALE));
    users.add(new User("Ahmet", 28, Gender.MALE));
    users.add(new User("Ayse", 24, Gender.FEMALE));
    users.add(user);
    return users;
  }

  @GetMapping("/search")
  List<User> search(@RequestParam String searchText) {
    List<User> users = new ArrayList<>();
    users.add(new User("Baris", 33, Gender.MALE));
    users.add(new User("Ahmet", 28, Gender.MALE));
    users.add(new User("Ayse", 24, Gender.FEMALE));
    System.out.println(searchText);
    return null;
  }
}
