package com.example.foodiebackend.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Getter
@Setter
public class Item
{
   @Id
    private int itemid;
   private String itemName;
   private float itemRating;
   private int review;
   private String catagory;
   private String description;
   private String foodpreference;
   private int itemprice;
   private List<UserRatingAndReviewToRestrauntItem> userRatingAndReviewList;

}
