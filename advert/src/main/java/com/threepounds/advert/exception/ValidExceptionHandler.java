package com.threepounds.advert.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse<Object>> handleValidationsExceptions(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName =((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );

      GeneralResponse<Object> objectGeneralResponse = new GeneralResponse<>();
      objectGeneralResponse.setErrors(errors);
      objectGeneralResponse.setData(null);
      return ResponseEntity.badRequest().body(objectGeneralResponse);
    }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<GeneralResponse<Object>> handleBadRequestExceptions(BadRequestException ex){

    GeneralResponse<Object> objectGeneralResponse = new GeneralResponse<>();
    objectGeneralResponse.setMessage(ex.getMessage());
    objectGeneralResponse.setData(null);
    return ResponseEntity.badRequest().body(objectGeneralResponse);
  }
}
