package com.hyper.autoshop.controller;

import com.hyper.autoshop.dto.user.EmailLoginRequest;
import com.hyper.autoshop.dto.user.EmailRegisterRequest;
import com.hyper.autoshop.dto.user.GoogleAuthRequest;
import com.hyper.autoshop.dto.user.AuthResponse;
import com.hyper.autoshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/google")
    public AuthResponse googleAuthentication(@RequestBody GoogleAuthRequest user) {
        return authService.googleAuthentication(user);
    }

    @PostMapping("/register")
    public AuthResponse emailRegister(@RequestBody EmailRegisterRequest user) {
        return authService.emailRegister(user);
    }

    @PostMapping("/login")
    public AuthResponse emailLogin(@RequestBody EmailLoginRequest user) {
        return authService.emailLogin(user);
    }

    @GetMapping("/info")
    public String info(){
        return "Server loaded";
    }

}
