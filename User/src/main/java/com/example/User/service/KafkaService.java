package com.example.User.service;

import com.example.User.dto.UserCreatedEvent;
import com.example.User.model.User;
import com.example.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaService {

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "create_profile_user")
    public void message(String message){

        try {
            UserCreatedEvent event = objectMapper.readValue(message, UserCreatedEvent.class);

            System.out.println("Получено событие о создании профиля: " + event.getPersonalNumber());

            User user = new User();

            user.setName(event.getName());
            user.setSurname(event.getSurname());
            user.setEmail(event.getEmail());
            user.setPersonalNumber(event.getPersonalNumber());

            userRepository.save(user);

            System.out.println("Локальная копия профиля " + event.getPersonalNumber() + " сохранена в базу данных.");
        } catch (Exception e){
            System.err.println("Ошибка обработки сообщения: " + e.getMessage());
        }
    }



}
