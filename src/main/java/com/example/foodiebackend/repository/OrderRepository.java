package com.example.foodiebackend.repository;

import com.example.foodiebackend.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,Integer> {
}
