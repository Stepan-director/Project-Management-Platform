package com.example.User.controller;

import com.example.User.dto.ProfileUpdateRequest;
import com.example.User.model.User;
import com.example.User.repository.UserRepository;
import com.example.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(
            @RequestAttribute("email") String email,
            @RequestBody ProfileUpdateRequest request) {

        User updatedUser = service.updateProfile(email, request);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile(@RequestAttribute("email") String email) {
        User user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("Профиль не найден"));

        return ResponseEntity.ok(user);
    }

}
