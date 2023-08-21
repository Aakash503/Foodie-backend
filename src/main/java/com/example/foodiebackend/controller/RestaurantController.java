package com.example.foodiebackend.controller;

import com.example.foodiebackend.domain.Admin;
import com.example.foodiebackend.domain.Item;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.exception.*;
import com.example.foodiebackend.security.SecurityTokenGenerator;
import com.example.foodiebackend.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    SecurityTokenGenerator securityTokenGenerator;

    private ResponseEntity<?> responseEntity;



//    http://localhost:8090/api/v1/registerRestaurant
    @PostMapping("/registerRestaurant")
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
       try{
           responseEntity=new ResponseEntity<>(restaurantService.saveRestaurant(restaurant), HttpStatus.CREATED);
       } catch (RestaurantAlreadyExistsException e) {
           throw new RestaurantAlreadyExistsException();
       }
       return responseEntity;
    }





//    http://localhost:8090/api/v1/restaurantlogin
    @PostMapping("/restaurantlogin")
    public ResponseEntity<?> loginCheck(@RequestBody Restaurant restaurant) {
        Restaurant result = restaurantService.login(restaurant.getRestaurantEmailid(), restaurant.getPassword());
        if (result != null) {
            Map<String, String> key = securityTokenGenerator.generateToken2(restaurant);
            return new ResponseEntity<>(key, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/additem/{restaurantEmailid}")
    public ResponseEntity<?> saveItem(@RequestBody Item item , @PathVariable String restaurantEmailid) throws ItemAlreadyExistsException{
        try {
            responseEntity=new ResponseEntity<>(restaurantService.addItemToRestaurant(item,restaurantEmailid),HttpStatus.CREATED);
        } catch (ItemAlreadyExistsException e) {
            throw new ItemAlreadyExistsException();
        }

        return responseEntity;
    }


//    http://localhost:8090/api/v1/getitemlist/parasrestraurants@gmail.com
    @GetMapping("/getitemlist/{restaurantEmailid}")
    public ResponseEntity<?> getAllItem(@PathVariable String restaurantEmailid) throws RestaurantNotFoundException {
        try {
            responseEntity=new ResponseEntity<>(restaurantService.getAllItems(restaurantEmailid),HttpStatus.CREATED);
        } catch (RestaurantNotFoundException e) {
            throw new RestaurantNotFoundException();
        }

        return responseEntity;
    }


    @GetMapping("/getRestaurantbyitsname/{restaurantName}")
    public  ResponseEntity<?> getRestaurantbyitsname(@PathVariable String restaurantName) throws RestaurantNotFoundException {
        responseEntity=new ResponseEntity<>(restaurantService.getRestaurantByitsName(restaurantName),HttpStatus.FOUND);
        return responseEntity;
    }


//    http://localhost:8090/api/v1/getRestaurantbyRestaurantEmailid/Foodplaza42@gmail.com
    @GetMapping("/getRestaurantbyRestaurantEmailid/{restaurantEmailid}")
    public  ResponseEntity<?> getRestaurantbyRestaurantEmailid(@PathVariable String restaurantEmailid) throws RestaurantNotFoundException {
        responseEntity=new ResponseEntity<>(restaurantService.getRestaurantByRestaurantEmailid(restaurantEmailid),HttpStatus.FOUND);
        return responseEntity;
    }

    @GetMapping("/getAllItemsOfRestaurantbyitsname/{restaurantName}")
    public  ResponseEntity<?> getAllItemsOfRestaurantbyitsname(@PathVariable String restaurantName){
        responseEntity=new ResponseEntity<>(restaurantService.getAllItemsOfRestaurantByItsName(restaurantName),HttpStatus.FOUND);
        return responseEntity;
    }


    @GetMapping("/getAllRestaurant")
    public ResponseEntity<?> getAllRestaurants(){
        responseEntity=new ResponseEntity<>(restaurantService.getAllRestaurant(),HttpStatus.OK);
        return responseEntity;
    }



    //
    @GetMapping("/searchBytype/{type}")
    public ResponseEntity<?> getRestaurantsBySearch(@PathVariable String type){
        responseEntity=new ResponseEntity<>(restaurantService.searchByType(type),HttpStatus.OK);
        return responseEntity;
    }



//   this method gives the searched food item available in the restaurant fooditem list
    @GetMapping("/searchiem/{restaurantName}/{itemName}")
    public ResponseEntity<?> findItemInRestaurantItemList(@PathVariable String restaurantName,@PathVariable String itemName) throws RestaurantNotFoundException, ItemNotFoundException {
        try {
            responseEntity=new ResponseEntity<>(restaurantService.getItemOfRestaurantByName(restaurantName,itemName),HttpStatus.FOUND);
        } catch (RestaurantNotFoundException e) {
            throw new RestaurantNotFoundException();
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
        return responseEntity;
    }



    @PostMapping("/addcoupon/{coupon}/{restaurantEmailid}")
    public ResponseEntity<?> addcoupontoList(@PathVariable String coupon,@PathVariable String restaurantEmailid){
        responseEntity=new ResponseEntity<>(restaurantService.addCouponsToList(coupon,restaurantEmailid),HttpStatus.CREATED);
        return responseEntity;
    }




//    this method add discount to the selected food item of restaurant and returns a map
    @PostMapping("/addDiscounttofooditem/{itemName}/{discount}/{restaurantEmailid}")
    public ResponseEntity<?> adddiscounttofooditem(@PathVariable String itemName,@PathVariable int discount,@PathVariable String restaurantEmailid) throws ItemAlreadyExistsException {
      try {
          responseEntity=new ResponseEntity<>(restaurantService.offerDiscountToParticularFoodItems(itemName,discount,restaurantEmailid),HttpStatus.CREATED);

      }catch (ItemAlreadyExistsException e){
          throw new ItemAlreadyExistsException();
      }
        return responseEntity;
    }





   //    this method gives the listmap of discounted fooditems of restaurant
    @GetMapping("/getdiscountedfooditemsmap/{restaurantEmailid}")
    public ResponseEntity<?> getdiscountedfooditemsmap(@PathVariable String restaurantEmailid){
        responseEntity=new ResponseEntity<>(restaurantService.getmapOfDiscountedFoodItems(restaurantEmailid),HttpStatus.FOUND);
        return responseEntity;
    }




   //   this method gives the discount value if available on particular food item of given restauarant
    @GetMapping("/getdiscountvalueoffooditem/{itemName}/{restaurantName}")
    public ResponseEntity<?> getdiscountvalue(@PathVariable String itemName,@PathVariable String restaurantName){
        responseEntity=new ResponseEntity<>(restaurantService.getdiscountavailableonParticularFoodItem(itemName,restaurantName),HttpStatus.FOUND);
        return responseEntity;
    }




   // this method is not working yet code-refoctoring is needed
//    @PostMapping("/discountOnOrderWithCoupon/{coupon}/{restaurantEmailid}/{percentDiscountOnOrderWithCoupon}")
//    public ResponseEntity<?> orderDiscountListwithCoupon(@PathVariable String coupon,@PathVariable String restaurantEmailid,@PathVariable int percentDiscountOnOrderWithCoupon) throws CouponAlreadyAddedException {
//        try{
//            responseEntity=new ResponseEntity<>(restaurantService.maplistOfDiscountAvailableOnPlacedOrder(coupon,restaurantEmailid,percentDiscountOnOrderWithCoupon),HttpStatus.CREATED);
//        }catch (CouponAlreadyAddedException e){
//          throw new CouponAlreadyAddedException();
//        }
//          return responseEntity;
//    }



    @PostMapping("/discountOnOrderWithCoupon/{coupon}/{restaurantEmailid}/{percentDiscountOnOrderWithCoupon}")
    public ResponseEntity<?> orderDiscountListwithCoupon(@PathVariable String coupon,@PathVariable String restaurantEmailid,@PathVariable int percentDiscountOnOrderWithCoupon) throws CouponAlreadyAddedException {
        try{
            responseEntity=new ResponseEntity<>(restaurantService.addDiscountCouponOnPlacedOrder(coupon,restaurantEmailid,percentDiscountOnOrderWithCoupon),HttpStatus.CREATED);
        }catch (CouponAlreadyAddedException e){
          throw new CouponAlreadyAddedException();
        }
          return responseEntity;
    }



//    this method gives the map of coupon and associated discount on placed order
    @GetMapping("/getDiscountOrderMap/{restaurantEmailid}")
    public ResponseEntity<?> DiscountListwithCoupon(@PathVariable String restaurantEmailid) throws CouponAlreadyAddedException {
        responseEntity=new ResponseEntity<>(restaurantService.getOrderDiscountMap(restaurantEmailid),HttpStatus.CREATED);
        return responseEntity;
    }



//    http://localhost:8090/api/v1/delete1/Foodculturerestraurants@gmail.com/special thali
    @DeleteMapping("/delete1/{restaurantEmailid}/{itemName}")
    public ResponseEntity<?> deleteItembyName(@PathVariable String restaurantEmailid, @PathVariable String itemName) throws Exception {
        responseEntity = new ResponseEntity<>(restaurantService.deleteItemByName(restaurantEmailid, itemName), HttpStatus.OK);
        return responseEntity;
    }


//    @PostMapping("/additemList/{restaurantEmailid}")
//    public ResponseEntity<?> saveItemList(@RequestBody List<Item> itemList, @PathVariable String restaurantEmailid) throws ItemAlreadyExistsException{
//        try {
//            responseEntity=new ResponseEntity<>(restaurantService.addListOfSelectedItemsToRestraunt(restaurantEmailid,itemList),HttpStatus.CREATED);
//        } catch (ItemAlreadyExistsException e) {
//            throw new ItemAlreadyExistsException();
//        }
//
//        return responseEntity;
//    }
}

