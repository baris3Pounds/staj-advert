package com.threepounds.advert.security;


import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.repository.UserRepository;
import com.threepounds.advert.rolePermisionUser.service.RoleService;
import com.threepounds.advert.rolePermisionUser.utils.mapper.UserMapper;
import java.util.Random;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;


import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Component

public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

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
      throw new RuntimeException("This email is already exist");
    }
    userRepository.save(user);

    return jwtService.generateToken(user.getUsername());
  }


}
