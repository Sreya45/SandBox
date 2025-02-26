package com.example.contactmanagement.repository;

import com.example.contactmanagement.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE phone_number = ?1", nativeQuery = true)
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE email = ?1", nativeQuery = true)
    void deleteByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET name = ?2, phone_number = ?3 WHERE email = ?1", nativeQuery = true)
    void updateUser(String email, String name, String phoneNumber);
}
