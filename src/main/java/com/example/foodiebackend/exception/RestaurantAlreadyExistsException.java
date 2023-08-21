package com.example.foodiebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "This restaurant already exists")
public class RestaurantAlreadyExistsException extends Throwable{
}
