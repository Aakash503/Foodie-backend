package com.example.foodiebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "This coupon is already added to the list")
public class CouponAlreadyAddedException extends Throwable{
}
