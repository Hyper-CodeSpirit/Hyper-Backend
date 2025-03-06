package com.hyper.autoshop.service;

import com.hyper.autoshop.constants.SecurityConstants;
import com.hyper.autoshop.dto.user.EmailLoginRequest;
import com.hyper.autoshop.dto.user.EmailRegisterRequest;
import com.hyper.autoshop.dto.user.GoogleAuthRequest;
import com.hyper.autoshop.dto.user.AuthResponse;
import com.hyper.autoshop.model.User;
import com.hyper.autoshop.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    public AuthResponse googleAuthentication(GoogleAuthRequest googleAuthRequest) {

        String token = generateToken(googleAuthRequest.getEmail(), googleAuthRequest.getAuthentication_method());
        AuthResponse authResponse = AuthResponse.builder().build();

        authResponse.setToken(token);
        authResponse.setHeader(SecurityConstants.JWT_HEADER);

        User existingUser = userRepo.findByEmail(googleAuthRequest.getEmail());
        if (existingUser != null && existingUser.getEmail().equals(googleAuthRequest.getEmail())) {

            if(existingUser.getAuthenticationMethod().equals("email")) {
                authResponse.setMessage("Email already in google authentication");
            }else {
                authResponse.setMessage("Email already in use");
            }
            return authResponse;
        }
        User user = User.builder().build();

        user.setUserName(googleAuthRequest.getUsername());
        user.setEmail(googleAuthRequest.getEmail());
        user.setAvatar(googleAuthRequest.getAvatar());
        user.setCreatedDate(googleAuthRequest.getCreated_date());
        user.setAuthenticationMethod(googleAuthRequest.getAuthentication_method());

        userRepo.save(user);
        authResponse.setMessage("Success");
        return authResponse;
    }

    public AuthResponse emailRegister(EmailRegisterRequest emailRegisterRequest) {

        String token = generateToken(emailRegisterRequest.getEmail(), emailRegisterRequest.getAuthentication_method());
        AuthResponse authResponse = AuthResponse.builder().build();
        authResponse.setToken(token);
        authResponse.setHeader(SecurityConstants.JWT_HEADER);

        User existingUser = userRepo.findByEmail(emailRegisterRequest.getEmail());
        if (existingUser != null && existingUser.getEmail().equals(emailRegisterRequest.getEmail())) {

            if(existingUser.getAuthenticationMethod().equals("google")) {
                authResponse.setMessage("Email already in google authentication");
            }else {
                authResponse.setMessage("Email already in use");
            }
            return authResponse;
        }

        User user = User.builder().build();
        user.setUserName(emailRegisterRequest.getUsername());
        user.setEmail(emailRegisterRequest.getEmail());
        user.setPassword(emailRegisterRequest.getPassword());
        user.setCreatedDate(emailRegisterRequest.getCreated_date());
        user.setAuthenticationMethod(emailRegisterRequest.getAuthentication_method());

        userRepo.save(user);
        authResponse.setMessage("Success");
        return authResponse;
    }

    public AuthResponse emailLogin(EmailLoginRequest emailLoginRequest) {
        String token = generateToken(emailLoginRequest.getEmail(), "email");
        AuthResponse authResponse = AuthResponse.builder().build();
        authResponse.setToken(token);
        authResponse.setHeader(SecurityConstants.JWT_HEADER);
        User existingUser = userRepo.findByEmail(emailLoginRequest.getEmail());

        System.out.println(existingUser.getPassword());
        System.out.println(emailLoginRequest.getPassword());
        if (existingUser.getEmail().equals(emailLoginRequest.getEmail())) {
            if(existingUser.getAuthenticationMethod().equals("google")) {
                authResponse.setMessage("Email already in google authentication");
            }else if(existingUser.getAuthenticationMethod().equals("email")) {
                if(existingUser.getPassword().equals(emailLoginRequest.getPassword())) {
                    authResponse.setMessage("Success");
                }else {
                    authResponse.setMessage("Invalid email or password");
                }
            }else {
                authResponse.setMessage("Invalid Request");
            }
        }
        else {
            authResponse.setMessage("User does not exist");
        }
        return authResponse;
    }

    private String generateToken(String email, String authMethod) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().issuer("Hyper Auto Shop").subject("JWT Token")
                .claim("email", email)
                .claim("authMethod", authMethod)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key).compact();
    }
}
