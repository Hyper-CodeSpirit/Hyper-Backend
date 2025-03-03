package com.hyper.autoshop.controller;

import com.hyper.autoshop.model.User;
import com.hyper.autoshop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/user")
    public List<User> getUser(){
        return userRepo.findAll();
    }
}
