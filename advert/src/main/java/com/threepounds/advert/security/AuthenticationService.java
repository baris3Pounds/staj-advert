package com.threepounds.advert.security;


import com.threepounds.advert.config.RabbitMQConfig;
import com.threepounds.advert.exception.BadRequestException;
import com.threepounds.advert.messaging.AdMessageModel;
import com.threepounds.advert.messaging.RabbitMQSender;
import com.threepounds.advert.messaging.UserMessageModel;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.repository.UserRepository;
import com.threepounds.advert.rolePermisionUser.service.RoleService;
import com.threepounds.advert.rolePermisionUser.utils.mapper.UserMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Component
public class AuthenticationService {

  private final RabbitTemplate rabbitTemplate;

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  private final UserMapper userMapper;
  private final RoleService roleService;

  private final RabbitMQSender<UserMessageModel> rabbitMQSender;

  public String signup(UserDto userDto) {
    User user = userMapper.userDtoToEntity(userDto);
    user.setActive(true);
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    Role userRole = roleService.findByCode("ROLE_USER")
        .orElseThrow(() -> new RuntimeException("Role not found"));
    List<Role> roles = new ArrayList<>();
    roles.add(userRole);
    user.setRoles(roles);
    Optional<User> emailEntry = userRepository.findByUsername(user.getUsername());
    if (emailEntry.isPresent()) {
      throw new BadRequestException("This email is already exist");
    }
    User savedUser = userRepository.save(user);

    rabbitMQSender.send(new UserMessageModel(savedUser.getId(),savedUser.getUsername()));
    System.out.println("Message sent for user id : " + savedUser.getId());

    return jwtService.generateToken(savedUser.getUsername());
  }

  public User signIn(SignInDto signInDto) {
    User user =
        userRepository
            .findByUsername(signInDto.getUsername())
            .orElseThrow(() -> new BadRequestException("Invalid username or password"));
    if (passwordEncoder.matches(signInDto.getPassword(),user.getPassword())) {
      return user;
    } else {
      throw new BadRequestException("Invalid password");
    }
  }
}
