package com.example.foodiebackend.service;

import com.example.foodiebackend.domain.*;
import com.example.foodiebackend.exception.*;
import com.example.foodiebackend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;



@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl() {
    }

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {

        Restaurant rest1=new Restaurant();
        if (restaurantRepository.findById(restaurant.getRestaurantEmailid()).isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }
        else {
//            rest1 = new Restaurant(restaurant.getRestaurantEmailid(), restaurant.getRestaurantName(), restaurant.getPassword(), restaurant.getRestaurantAddress(),
//                    restaurant.getCity(), restaurant.getPincode(), new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>());
            rest1.setRestaurantEmailid(restaurant.getRestaurantEmailid());
            rest1.setPassword(restaurant.getPassword());
            rest1.setRestaurantName(restaurant.getRestaurantName());
            rest1.setCity(restaurant.getCity());
            rest1.setRestaurantAddress(restaurant.getRestaurantAddress());
            rest1.setItemList(new ArrayList<>());
            rest1.setCouponList(new ArrayList<>());
            rest1.setPincode(restaurant.getPincode());
            rest1.setDiccountOnOrderMap(new HashMap<>());
            rest1.setDiscountedFoodItemsMap(new HashMap<>());

            float r1=restaurant.getRestaurantRating();
            int i=(int) r1;
            String y=String.valueOf(i);
            if ((i<3| y.isEmpty()|y.equals(null)) ){
                rest1.setRestaurantRating(3.0f);
                rest1.setCountedReviews(12);
            }
            else {
                rest1.setRestaurantRating(restaurant.getRestaurantRating());
                rest1.setCountedReviews(restaurant.getCountedReviews());
            }


        }

        return restaurantRepository.save(rest1);
    }


    @Override
    public Restaurant login(String restaurantEmailid, String password) {
        if (restaurantRepository.findById(restaurantEmailid).isPresent()) {
            Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();
            if (restaurant.getPassword().equals(password)) {
                return restaurant;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    @Override
    public List<Item> getAllItems(String restaurantEmailid) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantEmailid).get().getItemList();
    }

    @Override
    public List<Item> getAllItemsOfRestaurantByItsName(String restaurantName) {
        List<Item> itemList = getRestaurantByName(restaurantName).getItemList();
        return itemList;
    }


    @Override
    public Restaurant addItemToRestaurant(Item item, String restaurantEmailid) throws ItemAlreadyExistsException {

        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();
        List<Item> itemList1 = restaurant.getItemList();
        boolean flag = false;
        ListIterator<Item> iterator = itemList1.listIterator();

        float i1=item.getItemRating();
        String s=String.valueOf(i1);
        int review1;

        Random random=new Random();
        int max=20,min=12;
        review1=random.nextInt(max-min)+min;
        System.out.println(review1);
        int max1=5,min1=3;
        double rd=Math.random();
        float randv=(float) (rd*(max1-min1+1))+min1;
        float gn=Math.round(randv*100)/100;




        float fn=(float) Math.ceil(randv);
        System.out.println(randv);
        System.out.println(gn);
        System.out.println(fn);
        item.setItemRating(fn);

        item.setReview(review1);



        if (itemList1 == null) {
            itemList1.add(item);

        } else {

            while (iterator.hasNext()) {
                Item item1 = iterator.next();
                if (item1.getItemid() == item.getItemid()) {
                    flag = true;
                }

            }

            if (flag == true) {
                throw new ItemAlreadyExistsException();
            }

            itemList1.add(item);
            restaurant.setItemList(itemList1);
        }

        return restaurantRepository.save(restaurant);

    }


    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchByType(String type) {

        List<Restaurant> list1 = restaurantRepository.findAll();
        List<Restaurant> foundRestList = new ArrayList<>();
        for (Restaurant restaurant : list1) {


            for (Item item : restaurant.getItemList()) {

                if (item.getItemName().toLowerCase().contains(type.toLowerCase())) {
                    foundRestList.add(restaurant);
                    break;
                }
                if (item.getCatagory().toLowerCase().contains(type.toLowerCase())) {
                    foundRestList.add(restaurant);
                    break;
                }
                if (item.getFoodpreference().toLowerCase().contains(type.toLowerCase())) {
                    foundRestList.add(restaurant);
                    break;
                }

            }

        }

        return foundRestList;
    }

    @Override
    public Restaurant getRestaurantByRestaurantEmailid(String restaurantEmailid) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantEmailid).get();
    }


    @Override
    public Restaurant getRestaurantByitsName(String restaurantName) throws RestaurantNotFoundException {

        Restaurant restaurant1 = restaurantRepository.findByRestaurantName(restaurantName);
//        if(restaurant1==null){
//            throw new RestaurantNotFoundException();
//        }
        return restaurant1;
    }


    @Override
    public Restaurant getRestaurantByName(String restaurantName) {

        Restaurant restaurant1 = restaurantRepository.findByRestaurantName(restaurantName);
        return restaurant1;
    }


    @Override
    public List<String> addCouponsToList(String coupon, String restaurantEmailid) {
        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();
        List<String> couponList = restaurant.getCouponList();
        couponList.add(coupon);
        restaurant.setCouponList(couponList);
        restaurantRepository.save(restaurant);
        return couponList;
    }


    @Override
    public Map<String, Integer> offerDiscountToParticularFoodItems(String itemName, int discount, String restaurantEmailid) throws ItemAlreadyExistsException {
        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();

//        Map<String,Integer> discountMap1=new HashMap<>();
//        restaurant.setDiscountedFoodItemsMap(discountMap1);
//        discountMap1.put(itemName,discount);
//        Map<String,Integer> discountMap=restaurant.getDiscountedFoodItemsMap();

        Map<String, Integer> discountedFoodItemMap = restaurant.getDiscountedFoodItemsMap();

        if (discountedFoodItemMap == null) {
            discountedFoodItemMap.put(itemName, discount);
        } else {
            Iterator<String> iterator = discountedFoodItemMap.keySet().iterator();
            boolean flag = false;

            while (iterator.hasNext()) {
                String foodItemName = iterator.next();

                if (foodItemName.toLowerCase().equals(itemName.toLowerCase())) {
                    flag = true;
                }

            }
            if (flag == true) {
                throw new ItemAlreadyExistsException();
            }
            discountedFoodItemMap.put(itemName, discount);
        }
        restaurantRepository.save(restaurant);
        return discountedFoodItemMap;
    }


    @Override
    public Map<String, Integer> getmapOfDiscountedFoodItems(String restaurantEmailid) {
        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();
        return restaurant.getDiscountedFoodItemsMap();
    }


    @Override
    public int getdiscountavailableonParticularFoodItem(String itemName, String restaurantEmailid) {
        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();
        Map<String, Integer> discountedFoodItemsMap = restaurant.getDiscountedFoodItemsMap();
        int discount = discountedFoodItemsMap.get(itemName);
        return discount;
    }



//    @Override
//    public Map<String, Integer> maplistOfDiscountAvailableOnPlacedOrder(String coupon, String restaurantEmailid, int percentDiscountOnOrderWithCoupon) throws CouponAlreadyAddedException {
//        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();
//        Map<String, Integer> orderDiscountListMap = restaurant.getDiccountOnOrderMap();
//
//        if (orderDiscountListMap.isEmpty()) {
//            orderDiscountListMap.put(coupon, percentDiscountOnOrderWithCoupon);
//        } else {
//            for (String key : orderDiscountListMap.keySet()
//            ) {
//                if (key.toLowerCase().equals(coupon.toLowerCase())) {
//                    throw new CouponAlreadyAddedException();
//                } else {
//                    orderDiscountListMap.put(coupon, percentDiscountOnOrderWithCoupon);
//                }
//            }
//        }
//        restaurant.setDiccountOnOrderMap(orderDiscountListMap);
//        restaurantRepository.save(restaurant);
//        return orderDiscountListMap;
//    }


    @Override
    public Restaurant addDiscountCouponOnPlacedOrder(String coupon, String restaurantEmailid, int percentDiscountOnOrderWithCoupon) throws CouponAlreadyAddedException {
       Restaurant restaurant=restaurantRepository.findById(restaurantEmailid).get();
       Map<String,Integer> orderDiscountMap=restaurant.getDiccountOnOrderMap();
       boolean flag=true;

       if (orderDiscountMap==null){
           orderDiscountMap.put(coupon,percentDiscountOnOrderWithCoupon);
           restaurant.setDiccountOnOrderMap(orderDiscountMap);
           restaurantRepository.save(restaurant);
       }
        else {

            for (String key :
                   orderDiscountMap.keySet()) {
               if (key.equals(coupon)) {
                   flag=false;
               }
           }

           if (!flag){
               throw new CouponAlreadyAddedException();
           }
           else {
               orderDiscountMap.put(coupon,percentDiscountOnOrderWithCoupon);
               restaurant.setDiccountOnOrderMap(orderDiscountMap);
               restaurantRepository.save(restaurant);
           }

       }
        return restaurant;
    }

    @Override
    public Map<String, Integer> getOrderDiscountMap(String restaurantEmailid) {
        Restaurant restaurant=restaurantRepository.findById(restaurantEmailid).get();
        return restaurant.getDiccountOnOrderMap();
    }


    @Override
    public Item getItemOfRestaurantByName(String restaurantName, String itemName) throws RestaurantNotFoundException, ItemNotFoundException {

        Restaurant restaurant = getRestaurantByitsName(restaurantName);
        Item foundItem = new Item();

        if (restaurant == null) {
            throw new RestaurantNotFoundException();
        } else {

            List<Item> itemList = restaurant.getItemList();

            for (Item item : itemList) {
                if (item.getItemName().toLowerCase().equalsIgnoreCase(itemName)) {
                    foundItem.setItemName(item.getItemName());
                    foundItem.setCatagory(item.getCatagory());
                    foundItem.setDescription(item.getDescription());
                    foundItem.setItemprice(item.getItemprice());
                    foundItem.setFoodpreference(item.getFoodpreference());
                    foundItem.setItemid(item.getItemid());
                }
            }

            if (foundItem == null) {
                throw new ItemNotFoundException();
            }

        }

        return foundItem;
    }

    public Restaurant findRestauarentByEmailid(String restaurantEmailid) throws RestaurantNotFoundException {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantEmailid);
        if (!restaurant.isPresent()) {
            throw new RestaurantNotFoundException();
        }
        return restaurant.get();
    }


    @Override
    public Restaurant deleteItemByName(String restaurantEmailid, String itemName) throws Exception {
        boolean contectIdExists = false;

        if (restaurantRepository.findById(restaurantEmailid).isEmpty()) {
            throw new Exception("User Not Available");
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantEmailid).get();

        List<Item> contentsList = restaurant.getItemList();

        contectIdExists = contentsList.removeIf(i -> i.getItemName().equals(itemName));

        if (!contectIdExists) {
            throw new Exception("restaurant not available");
        }
        restaurant.setItemList(contentsList);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant deleteRestaurant(String restaurantEmailid) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant=restaurantRepository.findById(restaurantEmailid);
        if (!restaurant.isPresent()){
            throw new RestaurantNotFoundException();
        }
        restaurantRepository.delete(restaurantRepository.findById(restaurantEmailid).get());
        return restaurant.get();
    }

    @Override
    public List<AccesibleOrderPriceLimitForDiscount> addOrderlimitForDiscount(String restaurantEmailid,AccesibleOrderPriceLimitForDiscount access) {
        Restaurant rest=restaurantRepository.findById(restaurantEmailid).get();
        List<AccesibleOrderPriceLimitForDiscount> accessDiscountList=rest.getCouponDiscountList();
        if (accessDiscountList.isEmpty()|accessDiscountList==null){
            List<AccesibleOrderPriceLimitForDiscount> accessDiscountList1=new ArrayList<>();
            accessDiscountList1.add(access);
            rest.setCouponDiscountList(accessDiscountList1);
            restaurantRepository.save(rest);
            return accessDiscountList1;
        }
        else {
            for (AccesibleOrderPriceLimitForDiscount acc1:
                 accessDiscountList) {

               if (acc1.getCoupon().equals(access.getCoupon())){
                   acc1.setOrderlimitForDiscountWithCoupon(access.getOrderlimitForDiscountWithCoupon());
               }
            }
            return null;

        }
    }


}



//    public List<Item> addListOfSelectedItemsToRestraunt(String restaurantEmailid , List<Item> itemList) throws ItemAlreadyExistsException {
//
//      Restaurant restaurant=restaurantRepository.findById(restaurantEmailid).get();
//
//      boolean flag=true;
////        for (Item item: itemList
////             ) {
////             addItemToRestaurant(item,restaurantEmailid);
////        }
////        return true;
////
//
//
////
//
//
//
//        if(restaurant !=null){
//
//            List<Item> restaurantItemList=restaurant.getItemList();
//            for (Item item1:itemList
//                 ) {
//                if (restaurantItemList.stream().anyMatch(item -> item.getItemid().equals(item1.getItemid()))){
//
//                }
//
//            }
//
//        }
//
//       return restaurant.getItemList();
//    }
//
//}

