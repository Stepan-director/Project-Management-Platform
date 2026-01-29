package com.example.Autotenification.service;

import com.example.Autotenification.dto.LoginRequest;
import com.example.Autotenification.dto.LoginResponse;
import com.example.Autotenification.dto.RegisterRequest;
import com.example.Autotenification.model.AuthUser;
import com.example.Autotenification.repository.AuthRepository;
import com.example.Autotenification.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public AuthUser registerUser(RegisterRequest request){ // регистарция

        repository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("Пользователь с таким Email уже зарегистрирован: "
                            + request.getEmail());});

        AuthUser user = new AuthUser();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPersonalNumber(UUID.randomUUID());

        // создание профиля отправка через кафка
        AuthUser saveUser = repository.save(user);

        kafkaService.sendMessage(saveUser);

        return saveUser;
    }

    @Transactional
    public LoginResponse loginUser(LoginRequest request){ // вход в профиль
        AuthUser authUser = repository.findByEmail(request.getEmail()).orElseThrow(()
                -> new RuntimeException("Пользваотеля с email:" + request.getEmail() + " не найден!"));

        if(!passwordEncoder.matches(request.getPassword(), authUser.getPassword())){
            throw new RuntimeException("Неверный пароль! Попробуйте ввести снова");
        }

        // генерируем токен
        String token = jwtUtil.generateToken(authUser);

        // возвращаем ответ с данными для входа
        return new LoginResponse(token, authUser.getEmail());

    }

}
