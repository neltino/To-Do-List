package com.example.todoapp.service.ServiceImpl;

import com.example.todoapp.CustomException.CustomException;
import com.example.todoapp.dto.UserDto;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void create(UserDto userDto) {
        User userInDb = userRepo.findByEmail(userDto.getEmail());
        if (userInDb != null)
            throw new CustomException("User Already Exist !!!");

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {

        if(userRepo.findByEmailAndPassword(email, password) == null){
            throw new CustomException("Invalid Username and/or Password");
        }
        User user = userRepo.findByEmailAndPassword(email, password);

        return user;

    }
}
