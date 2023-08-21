package com.example.foodiebackend.domain;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Favorite {

    @Id
    private String restaurantEmailid;
    private String restaurantName;
    private String password;
    private String restaurantAddress;
    private String city;
    private String pincode;
    private List<Item> itemList;
    private List<String> couponList;
    private Map<String ,Integer> discountedFoodItemsMap;
    private Map<String,Integer> diccountOnOrderMap;

    public Favorite() {
    }

    public Favorite(String restaurantEmailid, String restaurantName, String restaurantAddress,
                    String city, String pincode, List<Item> itemList, List<String> couponList,
                    Map<String, Integer> discountedFoodItemsMap,
                    Map<String, Integer> diccountOnOrderMap) {

        this.restaurantEmailid = restaurantEmailid;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.city = city;
        this.pincode = pincode;
        this.itemList = itemList;
        this.couponList = couponList;
        this.discountedFoodItemsMap = discountedFoodItemsMap;
        this.diccountOnOrderMap = diccountOnOrderMap;
    }

    public String getRestaurantEmailid() {
        return restaurantEmailid;
    }

    public void setRestaurantEmailid(String restaurantEmailid) {
        this.restaurantEmailid = restaurantEmailid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<String> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<String> couponList) {
        this.couponList = couponList;
    }

    public Map<String, Integer> getDiscountedFoodItemsMap() {
        return discountedFoodItemsMap;
    }

    public void setDiscountedFoodItemsMap(Map<String, Integer> discountedFoodItemsMap) {
        this.discountedFoodItemsMap = discountedFoodItemsMap;
    }

    public Map<String, Integer> getDiccountOnOrderMap() {
        return diccountOnOrderMap;
    }

    public void setDiccountOnOrderMap(Map<String, Integer> diccountOnOrderMap) {
        this.diccountOnOrderMap = diccountOnOrderMap;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "restaurantEmailid='" + restaurantEmailid + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", password='" + password + '\'' +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", itemList=" + itemList +
                ", couponList=" + couponList +
                ", discountedFoodItemsMap=" + discountedFoodItemsMap +
                ", diccountOnOrderMap=" + diccountOnOrderMap +
                '}';
    }

}
