package com.example.foodiebackend.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UserGivenRestRatingAndReview {

    private String username;
    private String userEmail;
    private float userRatingToRestaurant;
    private String userReviewToGivenRestaurant;
}
