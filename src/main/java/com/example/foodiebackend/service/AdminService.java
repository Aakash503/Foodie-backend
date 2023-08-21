package com.example.foodiebackend.service;

import com.example.foodiebackend.domain.Admin;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.domain.User;
import com.example.foodiebackend.exception.RestaurantNotFoundException;
import com.example.foodiebackend.exception.UserAlreadyExistsException;
import com.example.foodiebackend.exception.UserNotFoundException;

import java.util.List;

public interface AdminService
{
    Admin saveAdmin(Admin admin) throws UserAlreadyExistsException;

    Admin login(String email, String password);

    Admin getAdminDetails(String email);

    List<User> seeUserList();

    List<Restaurant> seeRestaurantList();

    User deleteSelectedUser(String userEmail) throws UserNotFoundException;

    Restaurant deleteSelectedRestaurant(String restaurantEmailid) throws RestaurantNotFoundException;

}
