package com.threepounds.advert.security;


import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
import com.threepounds.advert.rolePermisionUser.dto.UserResponseDto;
import com.threepounds.advert.rolePermisionUser.entity.User;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final JwtService jwtService;


  @PostMapping("/signup")
  public ResponseEntity<GeneralResponse<Object>> signup(@RequestBody UserDto userDto) {

    GeneralResponse<Object> response = GeneralResponse.builder()
        .data(authenticationService.signup(userDto)).build();
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/signin")
  public ResponseEntity<GeneralResponse<Object>> signIn(@Valid @RequestBody SignInDto signInDto) {
    User user = authenticationService.signIn(signInDto);
    String token = jwtService.generateToken(user.getUsername());
    UserResponseDto dto = UserResponseDto
            .builder()
            .username(user.getUsername())
            .token(token).build();
    GeneralResponse<Object> response = GeneralResponse
            .builder()
            .errors(null)
            .data(dto)
            .build();
    return ResponseEntity.ok(response);
  }
}
