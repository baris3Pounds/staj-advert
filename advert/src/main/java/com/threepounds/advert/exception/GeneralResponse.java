package com.threepounds.advert.exception;

import java.util.Map;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class GeneralResponse<T> {

  private Map<String, String> errors;
  private T data;

}
