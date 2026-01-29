package com.example.Autotenification.controller;

import com.example.Autotenification.dto.LoginRequest;
import com.example.Autotenification.dto.LoginResponse;
import com.example.Autotenification.dto.RegisterRequest;
import com.example.Autotenification.model.AuthUser;
import com.example.Autotenification.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    private ResponseEntity<?> registerUser(@RequestBody RegisterRequest request){
        try {
            AuthUser user = service.registerUser(request);
            return ResponseEntity.ok("Пользователь зарегестрирован с номером: " + user.getPersonalNumber());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалось зарегестрироваться: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    private ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        try{
            LoginResponse response = service.loginUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(401).body("Не удалось войти в профиль: " + e.getMessage());
        }
    }
}
