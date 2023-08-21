package com.example.foodiebackend.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccesibleOrderPriceLimitForDiscount {

    private int discountAvailableOnCoupon;
    private String coupon;
    private int orderlimitForDiscountWithCoupon;
}
