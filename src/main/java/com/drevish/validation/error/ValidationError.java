package com.drevish.validation.error;

import lombok.AllArgsConstructor;
import lombok.Data;

//TODO: star and user-friendly info about password in registration page
//TODO: back-end and front-end double password check
//TODO: page for every showroom
//TODO: internatialization for errors and headers etc
@Data
@AllArgsConstructor
public class ValidationError {
  private String message;
  private String fieldName;
}
