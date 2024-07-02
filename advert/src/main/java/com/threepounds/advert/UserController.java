package com.threepounds.advert;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

  @Autowired
  private UserService userService;


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


}
