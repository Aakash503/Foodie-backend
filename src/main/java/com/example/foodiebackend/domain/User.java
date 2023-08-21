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
public class User
{
    @Id
    private String email;
    private String username;
    private long phoneNumber;
    private String password;
    private List<Order> orderList;
    private List<OrderDto> orderDtoList;
    private List<Favorite> favoriteList;
    private String address;
    private String city;
    private long pincode;
    private byte[] profilePicture;



//    public User(String email, String username, long phoneNumber, String password, List<Order> orderList, List<OrderDto> orderDtoList, List<Favorite> favoriteList, String address, String city, long pincode, byte[] profilePicture) {
//        this.email = email;
//        this.username = username;
//        this.phoneNumber = phoneNumber;
//        this.password = password;
//        this.orderList = orderList;
//        this.orderDtoList = orderDtoList;
//        this.favoriteList = favoriteList;
//        this.address = address;
//        this.city = city;
//        this.pincode = pincode;
//        this.profilePicture = profilePicture;
//    }

}
