package com.example.foodiebackend.security;

import com.example.foodiebackend.domain.Admin;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.domain.User;
import com.example.foodiebackend.security.SecurityTokenGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(User user) {
        Map<String, String> map = new HashMap<>();
        String jwtToken = Jwts.builder().setIssuer("myApp")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mysecret")
                .compact();

        map.put("token", jwtToken);
        map.put("Message", "successfully logging");
        return map;
    }


    @Override
    public Map<String, String> generateToken1(Admin admin) {
        Map<String, String> map = new HashMap<>();
        String jwtToken = Jwts.builder().setIssuer("myApp")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mysecret")
                .compact();

        map.put("token", jwtToken);
        map.put("Message", "successfully logging");
        return map;
    }


    @Override
    public Map<String, String> generateToken2(Restaurant restaurant) {
        Map<String, String> map = new HashMap<>();
        String jwtToken = Jwts.builder().setIssuer("myApp")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "mysecret")
                .compact();

        map.put("token", jwtToken);
        map.put("Message", "successfully logging");
        return map;
    }


}
