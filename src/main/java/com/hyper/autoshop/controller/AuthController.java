package com.hyper.autoshop.controller;

import com.hyper.autoshop.dto.user.UserRequest;
import com.hyper.autoshop.dto.user.UserResponse;
import com.hyper.autoshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/google")
    public UserResponse googleAuthentication(@RequestBody UserRequest user) {
        return authService.googleAuthService(user);
    }

}
