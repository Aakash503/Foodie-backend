package com.example.foodiebackend.security;



import com.example.foodiebackend.domain.Admin;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    public Map<String, String> generateToken(User user);
    public Map<String, String> generateToken1(Admin admin);
    public Map<String, String> generateToken2(Restaurant restaurant);
}
