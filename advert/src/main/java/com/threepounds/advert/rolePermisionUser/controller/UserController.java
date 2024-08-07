package com.threepounds.advert.rolePermisionUser.controller;

import java.util.List;
import java.util.UUID;

import com.threepounds.advert.ad.Ad;
import com.threepounds.advert.ad.AdMapper;
import com.threepounds.advert.ad.AdResource;
import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/users")
@RestController
public class UserController {

  private final UserService userService;
  private final AdMapper adMapper;

  public UserController(UserService userService, AdMapper adMapper) {
    this.userService = userService;
    this.adMapper = adMapper;
  }


  @PreAuthorize("hasAuthority('VIEW_USERS')")
  @GetMapping
  public List<User> getUsers() {
    return userService.list();
  }

  @PostMapping
  public User save(@RequestBody User user) {
    return userService.save(user);
  }

  @GetMapping("/by-name")
  public List<User> getUsersByName(@RequestParam String name) {
    return userService.listByName(name);
  }


  @PreAuthorize("hasAuthority('EDIT_USERS')")
  @PutMapping("/{userId}")
  public ResponseEntity<User> update(@PathVariable UUID userId, @RequestBody User user) {
    User existingUser =
        userService.getById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    existingUser.setAge(user.getAge());
    existingUser.setName(user.getName());
    existingUser.setGender(user.getGender());
    User updateUser = userService.save(existingUser);
    return ResponseEntity.ok().body(updateUser);
  }

  @GetMapping("/{userId}/favorites")
  public ResponseEntity<GeneralResponse<List<AdResource>>> getUserFavorites(@PathVariable UUID userId){
    User user = userService.getById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    List<Ad> favoriteAds = user.getFavoriteAds();
    List<AdResource> adResources = adMapper.adListToAdResourceList(favoriteAds);

    return ResponseEntity.ok().body(GeneralResponse.<List<AdResource>>builder().data(adResources).build());
  }

  // DeleteMapping
  @DeleteMapping("/{userId}")
  public ResponseEntity delete(@PathVariable UUID userId) {
    User existingUser =
        userService.getById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    userService.deleteUser(existingUser);
    return ResponseEntity.ok().build();
  }
}
