package com.example.foodiebackend.service;

import com.example.foodiebackend.domain.*;
import com.example.foodiebackend.exception.*;
import com.example.foodiebackend.repository.RestaurantRepository;
import com.example.foodiebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.example.foodiebackend.domain.Order.SEQUENCE_NAME;

@Service
public class UserServiceImpl implements UserService{



    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantServiceImpl restaurantService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


//    @Override
//    public User saveUser(User user) throws UserAlreadyExistsException {
//
//        User user1;
//        if (userRepository.findById(user.getEmail()).isPresent()) {
//            throw new UserAlreadyExistsException();
//        } else {
//            user1 = new User(user.getEmail(),user.getUsername(),user.getPhoneNumber(),user.getPassword(),new ArrayList<>(),new ArrayList<>(),user.getFavoriteList(),
//                    user.getAddress(),user.getCity(),user.getPincode());
//
//        }
//
//        return userRepository.save(user1);
//    }


   @Override
    public User login(String email, String password) {
        if (userRepository.findById(email).isPresent()) {
            User user = userRepository.findById(email).get();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    @Override
    public User getUser(String email) throws UserNotFoundException{
        return userRepository.findById(email).get();
    }


    @Override
    public User updateUserDetails(String email, User user) throws UserNotFoundException {
        Optional<User> user1=userRepository.findById(email);
        User userMain=user1.get();
        if(user1.isPresent())
        {

            String username1;
            long phoneNumber1;
            long phoneNumber2=0;
            long pin1;
            long pin2=0;
            String email1;
            String password1;
            String address1;
            String city1;

            List<OrderDto> orderDtoList1=user1.get().getOrderDtoList();


            if(user.getPassword().isEmpty())
            { password1=user1.get().getPassword();
            } else{
                password1=user.getPassword();
            }

            if(user.getEmail().isEmpty())
            { email1=user1.get().getEmail();
            } else{
                email1=user.getEmail();
            }



            if(user.getCity().isEmpty())
            { city1=user1.get().getCity();
            } else{
                city1=user.getCity();
            }


            if(user.getAddress().isEmpty())
            { address1=user1.get().getAddress();
            } else{
                address1=user.getAddress();
            }


            if(user.getUsername().isEmpty())
            { username1=user1.get().getUsername();
            } else{
                username1=user.getUsername();
            }


            if(phoneNumber2 == user.getPhoneNumber()){
                phoneNumber1=user1.get().getPhoneNumber();
            } else {
                phoneNumber1=user.getPhoneNumber();
            }



            if(pin2 == user.getPincode()){
                pin1=user1.get().getPincode();
            } else {
                pin1=user.getPincode();
            }



            userMain.setEmail(email1);
            userMain.setUsername(username1);
            userMain.setPhoneNumber(phoneNumber1);
            userMain.setPassword(password1);
            userMain.setOrderDtoList(orderDtoList1);
            userMain.setAddress(address1);
            userMain.setCity(city1);
            userMain.setPincode(pin1);
//            ResponseEntity<?> response = proxi.userUpdate(email,userMain);
//            System.out.println(response);
        }
        else
        {
            throw new UserNotFoundException();
        }
        User userMain2=userRepository.save(userMain);
        return userMain2;
    }


    @Override
    public List<Order> getAllOrderHistory(String email) throws UserNotFoundException {
        return userRepository.findById(email).get().getOrderList();
    }

    @Override
    public User addOrderToOrderHistory(Order order, String email) throws OrderAlreadyAddedException {


        User user = userRepository.findById(email).get();
        List<Order> orderList1 = user.getOrderList();
        boolean flag = false;
        ListIterator<Order> iterator = orderList1.listIterator();

        if (orderList1 == null) {
            orderList1.add(order);
        } else {

            while (iterator.hasNext()) {
                Order order1 = iterator.next();
                if (order1.getOrderId() == order.getOrderId()) {
                    flag = true;
                }

            }

            if (flag == true) {
                throw new OrderAlreadyAddedException();
            }

            orderList1.add(order);
            user.setOrderList(orderList1);
        }

        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public OrderDto userPlaceOrder(Order order, String email, String restauarantEmailid) throws RestaurantNotFoundException {

        Optional<User> user = userRepository.findById(email);
        List<Order> orderList1 = user.get().getOrderList();
        List<ItemDto> itemDtoList1 = order.getItemList();
        ListIterator<ItemDto> iterator = itemDtoList1.listIterator();

        OrderDto orderDto=new OrderDto();
        List<OrderDto> orderDtoList=user.get().getOrderDtoList();
        List<ItemDto> itemDtoList=new ArrayList<>();
        List<ItemDto> itemDtoList2=new ArrayList<>();


        int sum = 0;
        while (iterator.hasNext()) {

            ItemDto itemDto = iterator.next();
            String itemname = itemDto.getOrderedItemName();
            int qty = itemDto.getQuantity();

            Restaurant restaurant=restaurantRepository.findById(restauarantEmailid).get();
            Item foundItem=new Item();

            List<Item> itemList= restaurant.getItemList();

            for (Item item:itemList){

                if (item.getItemName().toLowerCase().equalsIgnoreCase(itemname)){
                    foundItem.setItemName(item.getItemName());
                    foundItem.setCatagory(item.getCatagory());
                    foundItem.setDescription(item.getDescription());
                    foundItem.setItemprice(item.getItemprice());
                    foundItem.setFoodpreference(item.getFoodpreference());
                    foundItem.setItemid(item.getItemid());

            ItemDto fooditemDto=new ItemDto(foundItem.getItemName(), qty, foundItem.getItemprice(), foundItem.getFoodpreference(), qty* foundItem.getItemprice());

                    fooditemDto.setPrice(foundItem.getItemprice());
                    fooditemDto.setQuantity(qty);
                    fooditemDto.setTotalCostOfOrderedFoodItem(foundItem.getItemprice()*qty);
                    fooditemDto.setFoodpreference(foundItem.getFoodpreference());
                    fooditemDto.setOrderedItemName(foundItem.getItemName());
                    itemDtoList.add(fooditemDto);

                }

            }

            int itemPrice= foundItem.getItemprice();
            sum = sum + (itemPrice * qty);

        }

        order.setOrderId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));

        Restaurant restaurant1=restaurantRepository.findById(restauarantEmailid).get();
//        Map<String,Integer> orderDiscountMap=restaurant.getDiccountOnOrderMap();
//        String c1=order.getCoupon();
//        System.out.println(c1);
//        int value=orderDiscountMap.get(c1);
//        System.out.println(value);

        order.setBill(sum);


//        if(order.getCoupon().isEmpty()|order.getCoupon().equals(null))
//        {
//        order.setBill(sum);
//        }
//        else {
//
//          boolean flag=true;
//            for (String key :
//                    orderDiscountMap.keySet()) {
//                if (key.equals(order.getCoupon())) {
//                    flag=false;
//
//                }
//
//            }
//            System.out.println(flag);

//            if (!flag){
//                int discount=orderDiscountMap.get(order.getCoupon());
//                double discountedPrice=(discount/100)*sum;
//                double bill= sum-discountedPrice;
//                System.out.println(bill);
//                order.setBill(bill);
//                System.out.println(order.getBill());
//            }
//
//        }

        List<Order> list=new ArrayList<>();
        list.add(order);
        orderList1.add(order);

        orderDto.setTotalbill(order.getBill());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setRestaurantName(restaurant1.getRestaurantName());
        orderDto.setRestaurantRating(restaurant1.getRestaurantRating());
        orderDto.setCountedReviews(restaurant1.getCountedReviews());
        orderDto.setCoupon(order.getCoupon());
        LocalDateTime orderedDateTime=LocalDateTime.parse(LocalDateTime.now().toString());
        LocalDate date=orderedDateTime.toLocalDate();
        LocalTime time=orderedDateTime.toLocalTime();
        orderDto.setOrderDate(date.toString());
        orderDto.setTime(time.toString());
        orderDto.setItemList(itemDtoList);
        orderDtoList.add(orderDto);


        user.get().setOrderList(orderList1);
        user.get().setOrderDtoList(orderDtoList);

        userRepository.save(user.get());
        return orderDto;


//     ye function kaam krta hai isme kuchbhi garbar mat krna

    }


   @Override
    public OrderDto userPlaceOrder1(Order order, String email, String restauarantEmailid) throws RestaurantNotFoundException {

        Optional<User> user = userRepository.findById(email);
        List<Order> orderList1 = user.get().getOrderList();
        List<ItemDto> itemDtoList1 = order.getItemList();
        ListIterator<ItemDto> iterator = itemDtoList1.listIterator();

        OrderDto orderDto=new OrderDto();
        List<OrderDto> orderDtoList=user.get().getOrderDtoList();
        List<ItemDto> itemDtoList=new ArrayList<>();
        List<ItemDto> itemDtoList2=new ArrayList<>();


        int sum = 0;
        while (iterator.hasNext()) {

            ItemDto itemDto = iterator.next();
            String itemname = itemDto.getOrderedItemName();
            int qty = itemDto.getQuantity();

            Restaurant restaurant=restaurantRepository.findById(restauarantEmailid).get();
            Item foundItem=new Item();

            List<Item> itemList= restaurant.getItemList();

            for (Item item:itemList){

                if (item.getItemName().toLowerCase().equalsIgnoreCase(itemname)){
                    foundItem.setItemName(item.getItemName());
                    foundItem.setCatagory(item.getCatagory());
                    foundItem.setDescription(item.getDescription());
                    foundItem.setItemprice(item.getItemprice());
                    foundItem.setFoodpreference(item.getFoodpreference());
                    foundItem.setItemid(item.getItemid());

                    ItemDto fooditemDto=new ItemDto(foundItem.getItemName(), qty, foundItem.getItemprice(), foundItem.getFoodpreference(), qty* foundItem.getItemprice());

                    fooditemDto.setPrice(foundItem.getItemprice());
                    fooditemDto.setQuantity(qty);
                    fooditemDto.setTotalCostOfOrderedFoodItem(foundItem.getItemprice()*qty);
                    fooditemDto.setFoodpreference(foundItem.getFoodpreference());
                    fooditemDto.setOrderedItemName(foundItem.getItemName());
                    itemDtoList.add(fooditemDto);

                }

            }

            int itemPrice= foundItem.getItemprice();
            sum = sum + (itemPrice * qty);

        }

        order.setOrderId(sequenceGeneratorService.getSequenceNumber(SEQUENCE_NAME));

        Restaurant restaurant1=restaurantRepository.findById(restauarantEmailid).get();
        Map<String,Integer> orderDiscountMap=restaurant1.getDiccountOnOrderMap();
        String c1=order.getCoupon();
        System.out.println(c1);
        int value=orderDiscountMap.get(c1);
        System.out.println(value);

//        order.setBill(sum);


        if(order.getCoupon().isEmpty()|order.getCoupon().equals(null))
        {
        order.setBill(sum);
        }
        else {

          boolean flag=true;
            for (String key :
                    orderDiscountMap.keySet()) {
                if (key.equals(order.getCoupon())) {
                    flag=false;

                }

            }
            System.out.println(flag);

            if (!flag){
                int discount=orderDiscountMap.get(order.getCoupon());
                System.out.println(discount);
                System.out.println(sum);
                double discountedPrice=(discount*sum)/100;
                System.out.println(discountedPrice);
                double bill= sum-discountedPrice;
                System.out.println(bill);
                order.setBill(bill);
                System.out.println(order.getBill());
            }

        }

        List<Order> list=new ArrayList<>();
        list.add(order);
        orderList1.add(order);

        orderDto.setTotalbill(order.getBill());
        orderDto.setOrderId(order.getOrderId());
        orderDto.setRestaurantName(restaurant1.getRestaurantName());
        orderDto.setRestaurantRating(restaurant1.getRestaurantRating());
        orderDto.setCountedReviews(restaurant1.getCountedReviews());
        orderDto.setCoupon(order.getCoupon());
        LocalDateTime orderedDateTime=LocalDateTime.parse(LocalDateTime.now().toString());
        LocalDate date=orderedDateTime.toLocalDate();
        LocalTime time=orderedDateTime.toLocalTime();
        orderDto.setOrderDate(date.toString());
        orderDto.setTime(time.toString());
        orderDto.setItemList(itemDtoList);
        orderDtoList.add(orderDto);


        user.get().setOrderList(orderList1);
        user.get().setOrderDtoList(orderDtoList);

        userRepository.save(user.get());
        return orderDto;


//     ye function kaam krta hai isme kuchbhi garbar mat krna

    }




    @Override
    public OrderDto reOrder(String email, int orderId) throws RestaurantNotFoundException {

        User user =userRepository.findById(email).get();
        List<Order> orderList=user.getOrderList();
        List<OrderDto> orderDtoList=user.getOrderDtoList();
        Order selectedOrder=new Order();
        boolean flag=false;

        for (Order order:orderList
             ) {
            if( order.getOrderId()==orderId){

//                selectedOrder.setOrderId(orderDto.getOrderId());
                selectedOrder.setBill(order.getBill());
                selectedOrder.setItemList(order.getItemList());
                selectedOrder.setCoupon(order.getCoupon());
                flag=true;
            }
        }

        if (flag){
             OrderDto orderDtoMain=new OrderDto();
            for (OrderDto orderDto:orderDtoList
            ) {
                if( orderDto.getOrderId()==orderId){
                    orderDtoMain.setRestaurantName(orderDto.getRestaurantName());}

            }
            return userPlaceOrder(selectedOrder,email,restaurantRepository.findByRestaurantName(orderDtoMain.getRestaurantName()).getRestaurantEmailid());
        }

        else {
           return null;
        }


    }

    @Override
    public User addRestaurantToFavorite(String email, Restaurant restaurant) throws UserNotFoundException, RestaurantAlreadyExistsException {

        User user = userRepository.findById(email).get();

        Favorite fav=new Favorite();
        fav.setRestaurantName(restaurant.getRestaurantName());
        fav.setRestaurantEmailid(restaurant.getRestaurantEmailid());
        fav.setPassword(restaurant.getPassword());
        fav.setRestaurantAddress(restaurant.getRestaurantAddress());
        fav.setCity(restaurant.getCity());
        fav.setPincode(restaurant.getPincode());
        fav.setCouponList(restaurant.getCouponList());
        fav.setDiccountOnOrderMap(restaurant.getDiccountOnOrderMap());
        fav.setDiscountedFoodItemsMap(restaurant.getDiscountedFoodItemsMap());
        fav.setItemList(restaurant.getItemList());
        boolean flag = false;

        List<Favorite> favoriteList1 = user.getFavoriteList();
        ListIterator<Favorite> iterator = favoriteList1.listIterator();

        if(favoriteList1==null)
        {
            favoriteList1.add(fav);
        }
        else {

            while (iterator.hasNext()) {
                Favorite favorite = iterator.next();
                if (favorite.getRestaurantEmailid() == fav.getRestaurantEmailid()) {
                    flag=true;
                }

            }

            if(flag==true){
                throw new RestaurantAlreadyExistsException();
            }

            favoriteList1.add(fav);
            user.setFavoriteList(favoriteList1);
        }

        return userRepository.save(user);

}







    @Override
    public User addRestaurantToFavorite1(String email, String restaurantEmailid) throws UserNotFoundException, RestaurantAlreadyExistsException, RestaurantNotFoundException {

        User user = userRepository.findById(email).get();
        Restaurant restaurant=restaurantService.findRestauarentByEmailid(restaurantEmailid);

        if(restaurant==null){
            throw new UserNotFoundException();
        }

        Favorite fav=new Favorite();
        fav.setRestaurantName(restaurant.getRestaurantName());
        fav.setRestaurantEmailid(restaurant.getRestaurantEmailid());
        fav.setPassword(restaurant.getPassword());
        fav.setRestaurantAddress(restaurant.getRestaurantAddress());
        fav.setCity(restaurant.getCity());
        fav.setPincode(restaurant.getPincode());
        fav.setCouponList(restaurant.getCouponList());
        fav.setDiccountOnOrderMap(restaurant.getDiccountOnOrderMap());
        fav.setDiscountedFoodItemsMap(restaurant.getDiscountedFoodItemsMap());
        fav.setItemList(restaurant.getItemList());
        boolean flag = false;

        List<Favorite> favoriteList1 = user.getFavoriteList();
        ListIterator<Favorite> iterator = favoriteList1.listIterator();

        if(favoriteList1==null)
        {
            favoriteList1.add(fav);
        }
        else {

            while (iterator.hasNext()) {
                Favorite favorite = iterator.next();
                if (favorite.getRestaurantEmailid().toLowerCase().equals(fav.getRestaurantEmailid().toLowerCase())) {
                    flag=true;
                }

            }

            if(flag==true){
                throw new RestaurantAlreadyExistsException();
            }

            favoriteList1.add(fav);
            user.setFavoriteList(favoriteList1);
        }

        return userRepository.save(user);

    }




    public User deleteFavorite(String email, String restaurantEmailid) throws Exception {
        boolean contectIdExists = false;

        if (userRepository.findById(email).isEmpty()) {
            throw new Exception("User Not Available");
        }

        User user = userRepository.findById(email).get();

        List<Favorite> contentsList = user.getFavoriteList();

        contectIdExists = contentsList.removeIf(i -> i.getRestaurantEmailid().equals(restaurantEmailid));

        if (!contectIdExists) {
            throw new Exception("restaurant not available");
        }
        user.setFavoriteList(contentsList);
        return userRepository.save(user);
    }

    @Override
    public User deleteFavoriteByRestaurantName(String email, String restaurantName) throws Exception {

        boolean contectIdExists = false;

        if (userRepository.findById(email).isEmpty()) {
            throw new Exception("User Not Available");
        }

        User user = userRepository.findById(email).get();

        List<Favorite> contentsList = user.getFavoriteList();

        contectIdExists = contentsList.removeIf(i -> i.getRestaurantName().equals(restaurantName));

        if (!contectIdExists) {
            throw new Exception("restaurant not available");
        }
        user.setFavoriteList(contentsList);
        return userRepository.save(user);
    }

    @Override
    public List<Favorite> getUserFavouriteList(String email) {
       User user=userRepository.findById(email).get();
       List<Favorite> userFavouriteList=user.getFavoriteList();

        return userFavouriteList;
    }

    @Override
    public List<OrderDto> getOrderHistory(String email) {
        return userRepository.findById(email).get().getOrderDtoList();
    }

    @Override
    public User deleteUser(String userEmail) throws UserNotFoundException {
        Optional<User> user=userRepository.findById(userEmail);

        if (!user.isPresent()){
            throw new UserNotFoundException();
        }
        userRepository.delete(user.get());
        return user.get();
    }


//    @Override
//    public User registerUser(User user1) throws UserAlreadyExistsException {
//        User user = new User(user1.getEmail(),user1.getUsername(),user1.getPhoneNumber(),user1.getPassword(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),user1.getAddress(),user1.getCity(), user1.getPincode(), user1.getProfilePicture());
//
//        if (userRepository.findById(user.getEmail()).isPresent())
//        {
//            throw new UserAlreadyExistsException();
//        }
//
//        return userRepository.save(user);
//    }





    @Override
    public User registerUser(User user) throws UserAlreadyExistsException, IOException {
        User user1=new User(user.getEmail(), user.getUsername(), user.getPhoneNumber(), user.getPassword(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(), user.getAddress(), user.getCity(), user.getPincode(), user.getProfilePicture());
        if (userRepository.findById(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        else {

            userRepository.save(user1);
        }
        return user1;
    }





    @Override
    public OrderDto findOrder(String email,int orderId) {

       User user= userRepository.findById(email).get();
       List<OrderDto> userOrderDtoList=user.getOrderDtoList();
       ListIterator<OrderDto> iterator=userOrderDtoList.listIterator();
       OrderDto mainOrderDto=new OrderDto();

       while(iterator.hasNext()){
           OrderDto favOrderDto=iterator.next();

            if(favOrderDto.getOrderId()==orderId)
            {
                mainOrderDto.setOrderId(orderId);
                mainOrderDto.setRestaurantName(favOrderDto.getRestaurantName());
                mainOrderDto.setRestaurantRating(favOrderDto.getRestaurantRating());
                mainOrderDto.setCountedReviews(favOrderDto.getCountedReviews());
                mainOrderDto.setUserRatingToRestaurant(favOrderDto.getUserRatingToRestaurant());
                mainOrderDto.setUserReviewToGivenRestaurant(favOrderDto.getUserReviewToGivenRestaurant());
                mainOrderDto.setItemList(favOrderDto.getItemList());
                mainOrderDto.setCoupon(favOrderDto.getCoupon());
                mainOrderDto.setTotalbill(favOrderDto.getTotalbill());
                mainOrderDto.setOrderDate(favOrderDto.getOrderDate());
                mainOrderDto.setTime(favOrderDto.getTime());

            }
       }
        return mainOrderDto;
    }



@Override
    public OrderDto giveRatingToRestaurant(String email,int orderId,float ratingToRestaurant){
        User user=userRepository.findById(email).get();

//        OrderDto orderDto=findOrder(email,orderId);
//        float userRestRating=orderDto.getUserRatingToRestaurant();
//        orderDto.setUserRatingToRestaurant(ratingToRestaurant);
//        userRepository.save(user);
//        return orderDto;

    float restRating=Math.round(ratingToRestaurant);

    List<OrderDto> userOrderDtoList=user.getOrderDtoList();
    ListIterator<OrderDto> iterator=userOrderDtoList.listIterator();
    OrderDto mainOrderDto=new OrderDto();

    while(iterator.hasNext()){
        OrderDto favOrderDto=iterator.next();

        if(favOrderDto.getOrderId()==orderId)
        {
            favOrderDto.setUserRatingToRestaurant(restRating);

            mainOrderDto.setOrderId(orderId);
            mainOrderDto.setRestaurantName(favOrderDto.getRestaurantName());
            mainOrderDto.setRestaurantRating(favOrderDto.getRestaurantRating());
            mainOrderDto.setCountedReviews(favOrderDto.getCountedReviews());
            mainOrderDto.setUserRatingToRestaurant(favOrderDto.getUserRatingToRestaurant());
            mainOrderDto.setUserReviewToGivenRestaurant(favOrderDto.getUserReviewToGivenRestaurant());
            mainOrderDto.setItemList(favOrderDto.getItemList());
            mainOrderDto.setCoupon(favOrderDto.getCoupon());
            mainOrderDto.setTotalbill(favOrderDto.getTotalbill());
            mainOrderDto.setOrderDate(favOrderDto.getOrderDate());
            mainOrderDto.setTime(favOrderDto.getTime());

            userRepository.save(user);

        }

    }

    return mainOrderDto;


    }


}
