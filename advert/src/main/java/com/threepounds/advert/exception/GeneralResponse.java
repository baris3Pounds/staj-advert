package com.threepounds.advert.exception;

import java.util.Map;
import lombok.Data;

@Data
public class GeneralResponse<T> {

  private Map<String, String> errors;
  private T data;

}
