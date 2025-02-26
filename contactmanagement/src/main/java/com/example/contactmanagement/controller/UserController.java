package com.example.contactmanagement.controller;

import com.example.contactmanagement.model.User;
import com.example.contactmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Add User
    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody User user) {
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    // Get All Users
    @GetMapping("/viewAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // Update User by Email
    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.email);
        if (existingUser.isPresent()) {
            userRepository.updateUser(user.email, user.name, user.phoneNumber);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", "User not found");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // Delete User by Email
    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.email);
        if (existingUser.isPresent()) {
            userRepository.deleteByEmail(user.email);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", "User not found");
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
