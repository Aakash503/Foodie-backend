package com.example.foodiebackend.service;

import com.example.foodiebackend.domain.AccesibleOrderPriceLimitForDiscount;
import com.example.foodiebackend.domain.Item;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.exception.*;

import java.util.List;
import java.util.Map;

public interface RestaurantService
{
     Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

     Restaurant login(String restaurantEmailid, String password);
     List<Item> getAllItems(String restaurantEmailid) throws RestaurantNotFoundException;

     List<Item> getAllItemsOfRestaurantByItsName(String restaurantName);
     Restaurant addItemToRestaurant(Item item, String restaurantEmailid) throws ItemAlreadyExistsException;

     List<Restaurant> getAllRestaurant();

     List<Restaurant> searchByType(String type);


     Restaurant getRestaurantByRestaurantEmailid(String restaurantEmailid) throws RestaurantNotFoundException;
     Restaurant getRestaurantByitsName(String restaurantName) throws RestaurantNotFoundException;

     Item getItemOfRestaurantByName(String restaurantName,String itemName) throws RestaurantNotFoundException, ItemNotFoundException;

     Restaurant getRestaurantByName(String restaurantName);
     List<String> addCouponsToList(String coupon,String restaurantEmailid);

     Map<String,Integer> offerDiscountToParticularFoodItems(String itemName, int discount,String restaurantEmailid) throws ItemAlreadyExistsException;

     Map<String,Integer> getmapOfDiscountedFoodItems(String restaurantEmailid);
     int getdiscountavailableonParticularFoodItem(String itemName,String restaurantEmailid);

//     Map<String,Integer> maplistOfDiscountAvailableOnPlacedOrder(String coupon, String restaurantEmailid, int percentDiscountOnOrderWithCoupon) throws CouponAlreadyAddedException;

     Restaurant addDiscountCouponOnPlacedOrder(String coupon,String restaurantEmailid, int percentDiscountOnOrderWithCoupon) throws CouponAlreadyAddedException;

     Map<String,Integer> getOrderDiscountMap(String restaurantEmailid);
     Restaurant deleteItemByName(String restaurantEmailid, String itemName) throws Exception;

//     List<Item> addListOfSelectedItemsToRestraunt(String restaurantEmailid , List<Item> itemList) throws ItemAlreadyExistsException;

     Restaurant deleteRestaurant(String restaurantEmailid) throws RestaurantNotFoundException;

     List<AccesibleOrderPriceLimitForDiscount> addOrderlimitForDiscount(String restaurantEmailid,AccesibleOrderPriceLimitForDiscount access);
}
