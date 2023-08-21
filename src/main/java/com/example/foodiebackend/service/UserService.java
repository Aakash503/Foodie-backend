package com.example.foodiebackend.service;

import com.example.foodiebackend.domain.*;
import com.example.foodiebackend.exception.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService
{
//    User saveUser(User user) throws UserAlreadyExistsException;

    public User login(String email, String password);

    User getUser(String email) throws UserNotFoundException;

    User updateUserDetails(String email, User user) throws UserNotFoundException;
    List<Order> getAllOrderHistory(String email) throws UserNotFoundException;
    User addOrderToOrderHistory(Order order, String email) throws OrderAlreadyAddedException;

    List<User> getAllUsers();

//    Order placeOrder(Order order,String email,String restauarantName) throws UserNotFoundException, ItemNotFoundException, RestaurantNotFoundException;

//    Order toplaceOrder(Order order, String email, String restauarantName);

    OrderDto userPlaceOrder(Order order, String email, String restauarantEmailid) throws RestaurantNotFoundException;

    OrderDto userPlaceOrder1(Order order, String email, String restauarantEmailid) throws RestaurantNotFoundException ;

    OrderDto reOrder(String email,int orderId) throws RestaurantNotFoundException;
   User addRestaurantToFavorite(String email, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyExistsException;

    User addRestaurantToFavorite1(String email, String restaurantEmailid) throws UserNotFoundException, RestaurantAlreadyExistsException, RestaurantNotFoundException;
//    List<Restaurant> searchByType(String type);

    public User deleteFavorite(String email, String restaurantEmailid) throws Exception;
    public User deleteFavoriteByRestaurantName(String email, String restaurantName) throws Exception;

    List<Favorite> getUserFavouriteList(String email);

    List<OrderDto> getOrderHistory(String email);

//    User registerUser(User user1) throws UserAlreadyExistsException;
     User deleteUser(String userEmail) throws UserNotFoundException;

    User registerUser(User user) throws UserAlreadyExistsException, IOException;

    OrderDto findOrder(String email,int orderId);
    public OrderDto giveRatingToRestaurant(String email,int orderId,float ratingToRestaurant);
}
