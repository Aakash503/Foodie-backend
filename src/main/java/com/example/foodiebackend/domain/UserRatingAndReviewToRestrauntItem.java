package com.example.foodiebackend.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter@ToString
public class UserRatingAndReviewToRestrauntItem {

    private String username;
    private String userEmail;
    private float userRatingToRestaurantItem;
    private String userReviewToGivenRestaurantItem;

}
