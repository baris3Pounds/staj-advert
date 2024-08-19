package com.threepounds.advert.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

  @NotBlank(message = "Not blank")
  private String username;
  private String password;
}
