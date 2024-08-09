package com.threepounds.advert.rolePermisionUser.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
  private String username;
  private String token;
}
