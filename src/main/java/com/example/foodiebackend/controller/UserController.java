package com.example.foodiebackend.controller;

import com.example.foodiebackend.domain.Order;
import com.example.foodiebackend.domain.OrderDto;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.domain.User;
import com.example.foodiebackend.exception.RestaurantAlreadyExistsException;
import com.example.foodiebackend.exception.RestaurantNotFoundException;
import com.example.foodiebackend.exception.UserAlreadyExistsException;
import com.example.foodiebackend.exception.UserNotFoundException;
import com.example.foodiebackend.security.SecurityTokenGenerator;
import com.example.foodiebackend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    private ResponseEntity<?> responseEntity;



//    @PostMapping("/userregistration")
//    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
//        try {
//            responseEntity = new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            throw new UserAlreadyExistsException();
//        }
//        return responseEntity;
//    }



    @PostMapping("/login")
    public ResponseEntity<?> loginCheck(@RequestBody User user) {
        User result = userService.login(user.getEmail(), user.getPassword());
        if (result != null) {
            Map<String, String> key = securityTokenGenerator.generateToken(user);
            return new ResponseEntity<>(key, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.NOT_FOUND);
        }
    }




//    http://localhost:8090/api/v2/getUser/aakash143@gmail.com
    @GetMapping("/getUser/{email}")
    public ResponseEntity<?> findUser(@PathVariable String email) throws UserNotFoundException {
        try {
            responseEntity=new ResponseEntity<>(userService.getUser(email),HttpStatus.FOUND);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

        return responseEntity;
    }




//    http://localhost:8090/api/v2/updateuserdetails/aakash143@gmail.com
    @PostMapping("/updateuserdetails/{email}")
    public ResponseEntity<?> updateUser( @PathVariable String email,@RequestBody User user) throws UserNotFoundException {

        try {
            responseEntity = new ResponseEntity<>(userService.updateUserDetails(email,user), HttpStatus.OK);
        }
        catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

        return responseEntity;
    }



//    @PostMapping("/placeorder/{email}/{restaurantName}")
//    public ResponseEntity<?> placeOrder(@RequestBody Order order, @PathVariable String email,@PathVariable String restaurantName) throws UserNotFoundException, ItemNotFoundException, RestaurantNotFoundException {
//
//
//        responseEntity=new ResponseEntity<>(userService.placeOrder(order,email,restaurantName),HttpStatus.CREATED);
//        return responseEntity;
//    }


//    @PostMapping("/placeorder/{email}/{restaurantName}")
//    public ResponseEntity<?> placeOrder(@RequestBody Order order, @PathVariable String email, @PathVariable String restaurantName) throws RestaurantNotFoundException {
//
//        responseEntity = new ResponseEntity<>(userService.toplaceOrder(order, email, restaurantName), HttpStatus.CREATED);
//        return responseEntity;
//    }




//   placeorder is working but coupon is always showing null hence method is not working with discount ,m/d needs to be refactored
//    http://localhost:8090/api/v2/userPlaceorder/akashkaushik45@gmail.com/Foodplaza42@gmail.com
    @PostMapping("/userPlaceorder/{email}/{restaurantEmailid}")
    public ResponseEntity<?> userplaceOrder(@RequestBody Order order, @PathVariable String email, @PathVariable String restaurantEmailid) throws RestaurantNotFoundException {

        responseEntity = new ResponseEntity<>(userService.userPlaceOrder(order, email, restaurantEmailid), HttpStatus.CREATED);
        return responseEntity;
    }


    @PostMapping("/userPlaceorder1/{email}/{restaurantEmailid}")
    public ResponseEntity<?> userplaceOrder1(@RequestBody Order order, @PathVariable String email, @PathVariable String restaurantEmailid) throws RestaurantNotFoundException {

        responseEntity = new ResponseEntity<>(userService.userPlaceOrder1(order, email, restaurantEmailid), HttpStatus.CREATED);
        return responseEntity;
    }



    @PostMapping("/reOrder/{email}/{orderId}")
    public ResponseEntity<?> reOrder( @PathVariable String email, @PathVariable int orderId) throws RestaurantNotFoundException {

        responseEntity = new ResponseEntity<>(userService.reOrder(email,orderId), HttpStatus.CREATED);
        return responseEntity;
    }




//    http://localhost:8090/api/v2/findOrderDto/aakash143@gmail.com/29
    @GetMapping("/findOrderDto/{email}/{orderId}")
    public ResponseEntity<?> findOrder( @PathVariable String email, @PathVariable int orderId)  {

        responseEntity = new ResponseEntity<>(userService.findOrder( email, orderId), HttpStatus.CREATED);
        return responseEntity;
    }



//    http://localhost:8090/api/v2/giveRatingToRestaurant/aakash143@gmail.com/29/4.1
    @PostMapping("/giveRatingToRestaurant/{email}/{orderId}/{ratingToRestaurant}")
    public ResponseEntity<?> giveRatingToRestaurant( @PathVariable String email, @PathVariable int orderId,@PathVariable float ratingToRestaurant)  {

        responseEntity = new ResponseEntity<>(userService.giveRatingToRestaurant( email, orderId,ratingToRestaurant), HttpStatus.CREATED);
        return responseEntity;
    }



    @PostMapping("/addRestrauntToFavorite/{email}")
    public  ResponseEntity<?> addRestrauntToFavorite(@PathVariable String email,@RequestBody Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyExistsException {
       try {
           responseEntity=new ResponseEntity<>(userService.addRestaurantToFavorite(email,restaurant),HttpStatus.CREATED);

       }catch (UserNotFoundException e){
           throw new UserNotFoundException();
       }catch(RestaurantAlreadyExistsException e){
           throw new RestaurantAlreadyExistsException();
       }
        return responseEntity;

    }




    @PostMapping("/addRestrauntToFavorite1/{email}/{restaurantEmailid}")
    public  ResponseEntity<?> addRestrauntToFavorite1(@PathVariable String email,@PathVariable String restaurantEmailid) throws UserNotFoundException, RestaurantAlreadyExistsException, RestaurantNotFoundException {
        try {
            responseEntity=new ResponseEntity<>(userService.addRestaurantToFavorite1(email,restaurantEmailid),HttpStatus.CREATED);

        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        } catch (RestaurantNotFoundException e) {
            throw new RestaurantNotFoundException();
        } catch(RestaurantAlreadyExistsException e){
            throw new RestaurantAlreadyExistsException();}
        return responseEntity;

    }


    @DeleteMapping("/delete/{email}/{restaurantEmailid}")
    public ResponseEntity<?> deleteFavoritebyRestaurantEmailid(@PathVariable String email, @PathVariable String restaurantEmailid) throws Exception {
        responseEntity = new ResponseEntity<>(userService.deleteFavorite(email, restaurantEmailid), HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping("/delete2/{email}/{restaurantName}")
    public ResponseEntity<?> deleteFavoritebyRestaurantName(@PathVariable String email, @PathVariable String restaurantName) throws Exception {
        responseEntity = new ResponseEntity<>(userService.deleteFavoriteByRestaurantName(email, restaurantName), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getUserFavouriteList/{email}")
    public ResponseEntity<?> getUserFavouriteList(@PathVariable String email){
        responseEntity=new ResponseEntity<>(userService.getUserFavouriteList(email),HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/getOrderHistory/{email}")
    public ResponseEntity<?> getOrderHistory(@PathVariable String email){
        responseEntity=new ResponseEntity<>(userService.getOrderHistory(email),HttpStatus.OK);
        return responseEntity;
    }


//        @PostMapping("/userregistration")
//    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
//        try {
//            responseEntity = new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            throw new UserAlreadyExistsException();
//        }
//        return responseEntity;
//    }



    @PostMapping("/userregistration")
    public ResponseEntity<?> registerUser(@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws UserAlreadyExistsException, IOException {
        User user1 = new ObjectMapper().readValue(user, User.class);
        try {
            user1.setProfilePicture(file.getBytes());
            return new ResponseEntity<>(userService.registerUser(user1),HttpStatus.OK);
        }
        catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        }
        catch (Exception e) {
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        try {
//            responseEntity = new ResponseEntity<>(userMovieService.registerUser(user), HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            throw new UserAlreadyExistsException();
//        }
//        return responseEntity;
    }

}
