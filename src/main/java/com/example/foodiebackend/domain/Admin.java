package com.example.foodiebackend.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Admin
{
    @Id
    private String email="kaushik.akash05@gmail.com";
    private String adminName="Aakash kaushik";
    private long phoneNumber=8630390233L;
    private String password="Spider28#s";
    private Address address;
//    private List<User> userlist;
//    private List<Restaurant> restaurantList;


}
