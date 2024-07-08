package com.threepounds.advert.user;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;


  public User save(User user){
    user.setActive(true);
    return userRepository.save(user);
  }

  public List<User> list(){
    return userRepository.findAll();
  }

  public List<User> listByName(String name){
    return userRepository.findByName(name);
  }

  public Optional<User> getById(UUID userId){
    return userRepository.findById(userId);
  }

  public void deleteUser(User user){
    userRepository.delete(user);
  }


}

