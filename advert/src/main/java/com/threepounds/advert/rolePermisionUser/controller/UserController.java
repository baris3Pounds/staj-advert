package com.threepounds.advert.rolePermisionUser.controller;

import java.util.List;
import java.util.UUID;

import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.ad.Ad;
import com.threepounds.advert.ad.AdMapper;
import com.threepounds.advert.ad.AdResource;
import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.resource.UserResource;
import com.threepounds.advert.rolePermisionUser.service.UserService;
import com.threepounds.advert.rolePermisionUser.utils.mapper.UserMapper;
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
import org.springframework.web.servlet.function.EntityResponse;


@RequestMapping("/users")
@RestController
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;
  private final AdMapper adMapper;

  public UserController(UserService userService, UserMapper userMapper,AdMapper adMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
    this.adMapper = adMapper;
  }


  @PreAuthorize("hasAuthority('VIEW_USERS')")
  @GetMapping
  public ResponseEntity<GeneralResponse<Object>> getUsers() {
    List<User> users = userService.list();
    List<UserResource> userResources = userMapper.userListToUserResourceList(users);
    return ResponseEntity.ok().body(GeneralResponse.<Object>builder().data(userResources).build());
  }

  @PostMapping
  public ResponseEntity<GeneralResponse<Object>> save(@RequestBody UserDto userDto) {
    User user = userMapper.userDtoToUser(userDto);
    userService.save(user);
    UserResource userResource = userMapper.userToUserResource(user);

    return ResponseEntity.ok().body(GeneralResponse.builder().data(userResource).build());
  }

  @GetMapping("/by-name")
  public ResponseEntity<GeneralResponse<Object>> getUsersByName(@RequestParam String name) {
    userService.listByName(name);
    UserResource userResource = userMapper.findByName(name);

    return ResponseEntity.ok().body(GeneralResponse.builder().data(userResource).build());
  }


  @PreAuthorize("hasAuthority('EDIT_USERS')")
  @PutMapping("/{userId}")
  public ResponseEntity<GeneralResponse<Object>> update(@PathVariable UUID userId, @RequestBody UserDto userDto) {
    User existingUser = userMapper.userDtoToUser(userDto);
        userService.getById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    existingUser.setAge(userDto.getAge());
    existingUser.setName(userDto.getName());
    existingUser.setGender(userDto.getGender());
    User updateUser = userService.save(existingUser);
    UserResource userResource = userMapper.userToUserResource(updateUser);
    return ResponseEntity.ok().body(GeneralResponse.builder().data(updateUser).build());
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
