package com.example.FlightBooking.Controllers;


import com.example.FlightBooking.Models.Users;
import com.example.FlightBooking.Repository.UserRepository;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/signUp")
    public ResponseEntity<Map<String,String>> signUp(@RequestBody Users u){

        Users Uobj = userRepository.save(u);
        Map<String,String> response = new HashMap<>();
        response.put("Status","User Added successfully");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Users u) {
        List<Users> userObj = userRepository.validation(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if (userObj.size() > 0) {
            response.put("Status", "Login Successful");
            response.put("Userid", String.valueOf(userObj.get(0).getUserId()));
            response.put("Name", String.valueOf(userObj.get(0).getName()));
        } else {
            response.put("Status", "Failed to login");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/viewDetails")
    public ResponseEntity<Map<String,String>> viewDetails(@RequestBody Users u){
        List<Map<String,String>> response = UserRepository.viewUserDetails(u.getUserId);
        return ResponseEntity.ok((Map<String, String>) response);
    }

    @Transactional
    @Modifying
    @PostMapping("/updateDetails")

}

