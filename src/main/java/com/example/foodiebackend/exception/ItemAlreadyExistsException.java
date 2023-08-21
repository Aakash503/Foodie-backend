package com.example.foodiebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "This item already exists")
public class ItemAlreadyExistsException extends Throwable{
}
