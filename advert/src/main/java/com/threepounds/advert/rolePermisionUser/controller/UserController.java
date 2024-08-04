package com.threepounds.advert.rolePermisionUser.controller;

import java.util.List;
import java.util.UUID;

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

  public UserController(UserService userService) {
    this.userService = userService;
  }

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


  // CREATE USER USERDTO kulllanılacak içerisinde List<UUID> roleIds

  // PutMapping
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

  // DeleteMapping
  @DeleteMapping("/{userId}")
  public ResponseEntity delete(@PathVariable UUID userId) {
    User existingUser =
        userService.getById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    userService.deleteUser(existingUser);
    return ResponseEntity.ok().build();
  }
}
