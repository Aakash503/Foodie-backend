package com.example.foodiebackend.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
public class Restaurant {

    @Id
    private String restaurantEmailid;
    private float restaurantRating;
    private int countedReviews;
    private String password;
    private long phoneNUmber;
    private String restaurantName;
    private String restaurantAddress;
    private String city;
    private String pincode;
    private List<Item> itemList;
    private List<String> couponList;
    private Map<String ,Integer> discountedFoodItemsMap;
    private Map<String,Integer> diccountOnOrderMap;
    private List<UserGivenRestRatingAndReview> usersRatingAndReviewToRestaurantList;
    private List<AccesibleOrderPriceLimitForDiscount> couponDiscountList;
}
