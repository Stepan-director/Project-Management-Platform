package com.example.User.service;

import com.example.User.dto.ProfileUpdateRequest;
import com.example.User.model.User;
import com.example.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // обнволение профиля
    @Transactional
    public User updateProfile(String email, ProfileUpdateRequest request) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден!"));

        user.setNumberPhone(request.getNumberPhone());
        user.setPosition(request.getPosition());

        return repository.save(user);
    }

    // поиск пользователя: имя + фамилия
    public List<User> searchUser(String name, String surname){
        return repository.findByNameAndSurname(name,surname);
    }



}
