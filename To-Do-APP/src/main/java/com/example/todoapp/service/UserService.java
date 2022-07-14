package com.example.todoapp.service;

import com.example.todoapp.dto.UserDto;
import com.example.todoapp.model.User;

public interface UserService{
    void create(UserDto userDto);

    User login(String email, String password);

}
