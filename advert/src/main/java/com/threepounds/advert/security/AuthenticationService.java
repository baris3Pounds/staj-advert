package com.threepounds.advert.security;


import com.threepounds.advert.exception.BadRequestException;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.repository.UserRepository;
import com.threepounds.advert.rolePermisionUser.service.RoleService;
import com.threepounds.advert.rolePermisionUser.utils.mapper.UserMapper;
import org.springframework.stereotype.Component;


import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Component

public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  private final UserMapper userMapper;
  private final RoleService roleService;

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
    userRepository.save(user);

    return jwtService.generateToken(user.getUsername());
  }

  public User signIn(UserDto userDto) {
    User user =
        userRepository
            .findByUsername(userDto.getUsername())
            .orElseThrow(() -> new BadRequestException("Invalid username or password"));
    if (user.getPassword().matches(passwordEncoder.encode(userDto.getPassword()))) {
      return user;
    } else {
      throw new BadRequestException("Invalid password");
    }
  }
}
