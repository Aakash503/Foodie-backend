package com.example.foodiebackend.repository;

import com.example.foodiebackend.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,String> {

    Restaurant findByRestaurantName(String restaurantName);
}
