package com.threepounds.advert;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


  public User save(User user){

    return userRepository.save(user);
  }

  public List<User> list(){
    return userRepository.findAll();
  }

  public List<User> listByName(String name){
    return userRepository.findByName(name);
  }


}
