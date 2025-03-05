package com.hyper.autoshop.service;

import com.hyper.autoshop.constants.SecurityConstants;
import com.hyper.autoshop.dto.user.UserRequest;
import com.hyper.autoshop.dto.user.UserResponse;
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

    public UserResponse googleAuthService(UserRequest userRequest) {

        String token = generateToken(userRequest.getEmail(), userRequest.getAuthentication_method());
        UserResponse userResponse = UserResponse.builder().build();

        userResponse.setToken(token);
        userResponse.setHeader(SecurityConstants.JWT_HEADER);

        User existingUser = userRepo.findByEmail(userRequest.getEmail());
        if (existingUser != null && existingUser.getEmail().equals(userRequest.getEmail())) {
            return userResponse;
        }
        User user = User.builder().build();

        user.setUserName(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setAvatar(userRequest.getAvatar());
        user.setCreatedDate(userRequest.getCreated_date());
        user.setAuthenticationMethod(userRequest.getAuthentication_method());

        userRepo.save(user);
        return userResponse;
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
