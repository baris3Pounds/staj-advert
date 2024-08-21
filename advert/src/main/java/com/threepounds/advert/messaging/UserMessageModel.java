package com.threepounds.advert.messaging;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMessageModel {
  private UUID id;
  private String username;
}
