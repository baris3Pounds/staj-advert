package com.threepounds.advert.exception;

import java.util.Map;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@Getter
@Setter
public class GeneralResponse<T> {

  private Map<String,String> errors;
  private T data;

  public GeneralResponse() {
  }

}
