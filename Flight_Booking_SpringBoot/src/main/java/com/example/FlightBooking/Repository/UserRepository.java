package com.example.FlightBooking.Repository;

import com.example.FlightBooking.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = ?1 AND password = ?2", nativeQuery = true)
    List<Users> validation(String email, String password);

    @Query(value = "SELECT * FROM users WHERE user_id = ?1", nativeQuery = true)
    Users viewDetails(int userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET name = ?2, age = ?3, email = ?4, password = ?5, phone_no = ?6 WHERE user_id = ?1", nativeQuery = true)
    void updateUser(int userId, String name, int age, String email, String password, String phoneNo);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users WHERE user_id = ?1", nativeQuery = true)
    void deleteUser(int userId);
}