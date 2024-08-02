package com.threepounds.advert.security;


import com.threepounds.advert.exception.GeneralResponse;
import com.threepounds.advert.rolePermisionUser.dto.UserDto;
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


  @PostMapping("/signup")
  public ResponseEntity<GeneralResponse> signup(@RequestBody UserDto userDto) {

    GeneralResponse<Object> response = GeneralResponse.builder()
        .data(authenticationService.signup(userDto)).build();
    return ResponseEntity.ok().body(response);
  }
}
