package com.threepounds.advert;


import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public List<User> getUsers(){
     return userService.list();
  }


  @PostMapping
  public User save(@RequestBody User user){
    return userService.save(user);
  }

  @GetMapping("/by-name")
  public List<User> getUsersByName(@RequestParam String name){
    return userService.listByName(name);
  }

  // PutMapping
  @PutMapping("/{userId}")
  public User update(@PathVariable UUID userId,@RequestBody User user){
    //
    User existingUser = userService.getById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    existingUser.setAge(user.getAge());
    existingUser.setName(user.getName());
    userService.save(existingUser);

    return null;
  }

  // DeleteMapping
  @DeleteMapping("/{userId}")
  public User delete(@PathVariable UUID userId){
    User existingUser = userService.getById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
         userRepository.delete(existingUser);

      return null;
  }

}
