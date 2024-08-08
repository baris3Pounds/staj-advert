package com.threepounds.advert.security;

import com.threepounds.advert.rolePermisionUser.utils.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class SignInDto {

  @NotBlank(message = "Not blank")
  private String username;


  @NotBlank(message = "Not blank")
  private String password;

}
