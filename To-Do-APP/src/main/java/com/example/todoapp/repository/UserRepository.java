package com.example.todoapp.repository;


import com.example.todoapp.dto.UserDto;
import com.example.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//
//    User findUserEmailAndPassword (String email, String password);
    @Query(value = "SELECT * FROM users WHERE email = ? AND password = ?", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);



}

