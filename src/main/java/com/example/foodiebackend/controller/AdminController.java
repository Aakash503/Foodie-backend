package com.example.foodiebackend.controller;

import com.example.foodiebackend.domain.Admin;
import com.example.foodiebackend.exception.RestaurantNotFoundException;
import com.example.foodiebackend.exception.UserAlreadyExistsException;
import com.example.foodiebackend.exception.UserNotFoundException;
import com.example.foodiebackend.security.SecurityTokenGenerator;
import com.example.foodiebackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v3")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    private ResponseEntity<?> responseEntity;

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) throws UserAlreadyExistsException {
        try{
            responseEntity=new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
           return responseEntity;
    }


    @PostMapping("/adminlogin")
    public ResponseEntity<?> loginCheck(@RequestBody Admin admin) {
        Admin result = adminService.login(admin.getEmail(), admin.getPassword());
        if (result != null) {
            Map<String, String> key = securityTokenGenerator.generateToken1(admin);
            return new ResponseEntity<>(key, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.NOT_FOUND);
        }
    }




//    http://localhost:8090/api/v3/getAdmin/kaushik.akash05@gmail.com
    @GetMapping("/getAdmin/{email}")
    public ResponseEntity<?> getAdmin (@PathVariable String email){
        return new ResponseEntity<>(adminService.getAdminDetails(email),HttpStatus.OK);
    }


    @GetMapping("/getUsersList")
    public ResponseEntity<?> getUsersList(){
        return new ResponseEntity<>(adminService.seeUserList(),HttpStatus.OK);
    }


    @GetMapping("/getRestaurantsList")
    public ResponseEntity<?> getRestaurantsList(){
        return new ResponseEntity<>(adminService.seeRestaurantList(),HttpStatus.OK);
    }


    @DeleteMapping("/deleteSelectedUserByAdmin/{userEmail}")
    public ResponseEntity<?> deleteUser(@PathVariable String userEmail) throws UserNotFoundException {
        try {
            responseEntity=new ResponseEntity<>(adminService.deleteSelectedUser(userEmail),HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }



    @DeleteMapping("/deleteSelectedRestaurantByAdmin/{restaurantEmailid}")
    public ResponseEntity<?> deleteRestaurantByAdmin(@PathVariable String restaurantEmailid) throws UserNotFoundException {
        try {
            responseEntity=new ResponseEntity<>(adminService.deleteSelectedRestaurant(restaurantEmailid),HttpStatus.OK);
        }
         catch (RestaurantNotFoundException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }


}
