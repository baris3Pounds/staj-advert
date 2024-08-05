package com.threepounds.advert.exception;



public class BadRequestException extends RuntimeException {

  public BadRequestException(String message) {
    super(message);
  }
}
