package com.threepounds.advert.security;


import com.threepounds.advert.rolePermisionUser.entity.Role;
import com.threepounds.advert.rolePermisionUser.entity.User;
import com.threepounds.advert.rolePermisionUser.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements UserDetailsService{
  private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
      User user = userRepository.findByUsername(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      List<GrantedAuthority> authorities =
          buildUserAuthority(new HashSet<>(user.getRoles()));
      return buildUserForAuthentication(user, authorities);
    }


  private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        user.isActive(), true, true, true, authorities) {
    };
  }

  private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    // add user's authorities
    for (Role userRole : userRoles) {
      setAuths.add(new SimpleGrantedAuthority(userRole.getCode()));
      userRole.getPermissions().stream()
          .map(p -> new SimpleGrantedAuthority(p.getCode()))
          .forEach(setAuths::add);
    }

    return new ArrayList<GrantedAuthority>(setAuths);

  }
}
