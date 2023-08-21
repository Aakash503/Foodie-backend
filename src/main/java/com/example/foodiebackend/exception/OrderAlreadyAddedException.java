package com.example.foodiebackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "This order is already added to order history")
public class OrderAlreadyAddedException extends Throwable {
}
