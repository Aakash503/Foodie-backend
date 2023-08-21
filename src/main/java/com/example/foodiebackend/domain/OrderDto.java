package com.example.foodiebackend.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto
{
    @Id
    private int orderId;
    private String restaurantName;

    private float restaurantRating;

    private float userRatingToRestaurant;

    private String userReviewToGivenRestaurant;

    private int countedReviews;
    private List<ItemDto> itemList;
    private double totalbill;

    private String coupon;

    private String orderDate;
    private String time;
    public OrderDto() {
    }

    public OrderDto(int orderId, String restaurantName, List<ItemDto> itemList, double totalbill, String orderDate, String time) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.itemList = itemList;
        this.totalbill = totalbill;
        this.orderDate = orderDate;
        this.time = time;
    }

    public OrderDto(int orderId, String restaurantName, List<ItemDto> itemList, double totalbill, String coupon, String orderDate, String time) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.itemList = itemList;
        this.totalbill = totalbill;
        this.coupon = coupon;
        this.orderDate = orderDate;
        this.time = time;
    }



    public OrderDto(int orderId, String restaurantName, float restaurantRating, float userRatingToRestaurant, String userReviewToGivenRestaurant, int countedReviews, List<ItemDto> itemList, double totalbill, String coupon, String orderDate, String time) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.restaurantRating = restaurantRating;
        this.userRatingToRestaurant = userRatingToRestaurant;
        this.userReviewToGivenRestaurant = userReviewToGivenRestaurant;
        this.countedReviews = countedReviews;
        this.itemList = itemList;
        this.totalbill = totalbill;
        this.coupon = coupon;
        this.orderDate = orderDate;
        this.time = time;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDto> itemList) {
        this.itemList = itemList;
    }

    public double getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(double totalbill) {
        this.totalbill = totalbill;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public float getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(float restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    public int getCountedReviews() {
        return countedReviews;
    }

    public void setCountedReviews(int countedReviews) {
        this.countedReviews = countedReviews;
    }

    public float getUserRatingToRestaurant() {
        return userRatingToRestaurant;
    }

    public void setUserRatingToRestaurant(float userRatingToRestaurant) {
        this.userRatingToRestaurant = userRatingToRestaurant;
    }



    public String getUserReviewToGivenRestaurant() {
        return userReviewToGivenRestaurant;
    }

    public void setUserReviewToGivenRestaurant(String userReviewToGivenRestaurant) {
        this.userReviewToGivenRestaurant = userReviewToGivenRestaurant;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderId=" + orderId +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantRating=" + restaurantRating +
                ", userRatingToRestaurant=" + userRatingToRestaurant +
                ", userReviewToGivenRestaurant='" + userReviewToGivenRestaurant + '\'' +
                ", countedReviews=" + countedReviews +
                ", itemList=" + itemList +
                ", totalbill=" + totalbill +
                ", coupon='" + coupon + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
