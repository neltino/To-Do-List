package com.example.todoapp.controller;

import com.example.todoapp.dto.UserDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.ServiceImpl.UserServiceImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.http.HttpSession;


@RestController
@Data
@Scope("session")

public class UserController{
   private final HttpSession httpSession;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/signup")
    public String createAccount(@RequestBody UserDto userDto) {
        userService.create(userDto);
        return "User created";
    }

    
        @GetMapping("/login")
        public String login(@RequestParam String email, @RequestParam String password) {
        User oauthUser = userService.login(email, password);
            httpSession.setAttribute("user_id", oauthUser.getId());
            return "Welcome " + oauthUser.getFirstname() + "!";
        }

        @GetMapping("/logout")
        public String logout(){
            if(httpSession.getAttribute("user_id") == null){
                return "You are no longer logged in!";
            }else{
                httpSession.invalidate();
                return "Logout successful!";
            }

        }
    }
