package com.example.foodiebackend.service;

import com.example.foodiebackend.domain.Address;
import com.example.foodiebackend.domain.Admin;
import com.example.foodiebackend.domain.Restaurant;
import com.example.foodiebackend.domain.User;
import com.example.foodiebackend.exception.RestaurantNotFoundException;
import com.example.foodiebackend.exception.UserAlreadyExistsException;
import com.example.foodiebackend.exception.UserNotFoundException;
import com.example.foodiebackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Admin saveAdmin(Admin admin) throws UserAlreadyExistsException {
        Admin admin1=new Admin();
        if(adminRepository.findById(admin.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        else {
            admin1.setEmail(admin.getEmail());
            admin1.setAdminName(admin.getAdminName());
            admin1.setPassword(admin.getPassword());
            admin1.setAddress(admin.getAddress());
            admin1.setPhoneNumber(admin.getPhoneNumber());

//            admin1.setUserlist(new ArrayList<>());
//            admin1.setRestaurantList(new ArrayList<>());

        }
        return adminRepository.save(admin1);
    }


    @Override
    public Admin login(String email, String password) {
        if(email.equals("kaushik.akash05@gmail.com") && password.equals("Spider28#s")){
            Admin admin=new Admin();
            Address address=new Address();
            admin.setAddress(address);
//            admin.setUserlist(userService.getAllUsers());
//            admin.setRestaurantList(restaurantService.getAllRestaurant());
            if (!adminRepository.findById(email).isPresent()){
                adminRepository.save(admin);
            }
          return admin;
        }
        else if (adminRepository.findById(email).isPresent()) {
            Admin admin = adminRepository.findById(email).get();
            if (admin.getPassword().equals(password)) {
                return admin;
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }


    @Override
    public Admin getAdminDetails(String email){
        return adminRepository.findById(email).get();
    }

    @Override
    public List<User> seeUserList() {
        return userService.getAllUsers();
    }

    @Override
    public List<Restaurant> seeRestaurantList() {
        return restaurantService.getAllRestaurant();
    }

    @Override
    public User deleteSelectedUser(String userEmail) throws UserNotFoundException {
        return userService.deleteUser(userEmail);
    }

    @Override
    public Restaurant deleteSelectedRestaurant(String restaurantEmailid) throws RestaurantNotFoundException {
        return restaurantService.deleteRestaurant(restaurantEmailid);
    }


}
