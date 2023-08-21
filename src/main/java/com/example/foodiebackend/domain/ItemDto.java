package com.example.foodiebackend.domain;

 public class ItemDto
{
    private String orderedItemName;
    private int quantity;
    private int price;
    private String foodpreference;

    private float userRatingToRestaurantItem;
    private String userReviewToGivenRestaurantItem;

    private int totalCostOfOrderedFoodItem;
    public ItemDto() {
    }

    public ItemDto(String orderedItemName, int quantity) {
        this.orderedItemName = orderedItemName;
        this.quantity = quantity;
    }

    public ItemDto(String orderedItemName, int quantity, int price, String foodpreference, int totalCostOfOrderedFoodItem) {
        this.orderedItemName = orderedItemName;
        this.quantity = quantity;
        this.price = price;
        this.foodpreference = foodpreference;
        this.totalCostOfOrderedFoodItem = totalCostOfOrderedFoodItem;
    }


    public ItemDto(String orderedItemName, int quantity, int price, String foodpreference, float userRatingToRestaurantItem, String userReviewToGivenRestaurantItem, int totalCostOfOrderedFoodItem) {
        this.orderedItemName = orderedItemName;
        this.quantity = quantity;
        this.price = price;
        this.foodpreference = foodpreference;
        this.userRatingToRestaurantItem = userRatingToRestaurantItem;
        this.userReviewToGivenRestaurantItem = userReviewToGivenRestaurantItem;
        this.totalCostOfOrderedFoodItem = totalCostOfOrderedFoodItem;
    }

    public String getFoodpreference() {
        return foodpreference;
    }

    public void setFoodpreference(String foodpreference) {
        this.foodpreference = foodpreference;
    }

    public int getTotalCostOfOrderedFoodItem() {
        return totalCostOfOrderedFoodItem;
    }

    public void setTotalCostOfOrderedFoodItem(int totalCostOfOrderedFoodItem) {
        this.totalCostOfOrderedFoodItem = totalCostOfOrderedFoodItem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderedItemName() {
        return orderedItemName;
    }

    public void setOrderedItemName(String orderedItemName) {
        this.orderedItemName = orderedItemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public float getUserRatingToRestaurantItem() {
        return userRatingToRestaurantItem;
    }

    public void setUserRatingToRestaurantItem(float userRatingToRestaurantItem) {
        this.userRatingToRestaurantItem = userRatingToRestaurantItem;
    }

    public String getUserReviewToGivenRestaurantItem() {
        return userReviewToGivenRestaurantItem;
    }

    public void setUserReviewToGivenRestaurantItem(String userReviewToGivenRestaurantItem) {
        this.userReviewToGivenRestaurantItem = userReviewToGivenRestaurantItem;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "orderedItemName='" + orderedItemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", foodpreference='" + foodpreference + '\'' +
                ", userRatingToRestaurantItem=" + userRatingToRestaurantItem +
                ", userReviewToGivenRestaurantItem='" + userReviewToGivenRestaurantItem + '\'' +
                ", totalCostOfOrderedFoodItem=" + totalCostOfOrderedFoodItem +
                '}';
    }
}
