package com.example.foodiebackend.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection = "orders")
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private int orderId;
    private List<ItemDto> itemList;
    private double bill;

    private String foodItemPrice;

    private String coupon;

    public Order() {
    }

    public Order( List<ItemDto> itemList, double bill) {

        this.itemList = itemList;
        this.bill = bill;
    }


    public Order( List<ItemDto> itemList, double bill, String foodItemPrice) {

        this.itemList = itemList;
        this.bill = bill;
        this.foodItemPrice = foodItemPrice;
    }


    public Order( List<ItemDto> itemList, double bill, String foodItemPrice, String coupon) {

        this.itemList = itemList;
        this.bill = bill;
        this.foodItemPrice = foodItemPrice;
        this.coupon = coupon;
    }


    public String getFoodItemPrice() {
        return foodItemPrice;
    }

    public void setFoodItemPrice(String foodItemPrice) {
        this.foodItemPrice = foodItemPrice;
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

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", itemList=" + itemList +
                ", bill=" + bill +
                ", foodItemPrice='" + foodItemPrice + '\'' +
                ", coupon='" + coupon + '\'' +
                '}';
    }


}
