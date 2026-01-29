package com.example.Autotenification.service;

import com.example.Autotenification.dto.CreateProfileRequest;
import com.example.Autotenification.dto.RegisterRequest;
import com.example.Autotenification.dto.UserCreatedEvent;
import com.example.Autotenification.model.AuthUser;
import org.apache.kafka.common.internals.Topic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@Service
public class KafkaService {

    private static final String Topic  = "create_profile_user";

    private final KafkaTemplate<UUID, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaService(KafkaTemplate<UUID, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(AuthUser user){
        try{
            UserCreatedEvent event = new UserCreatedEvent();
            event.setId(user.getId());
            event.setName(user.getName());
            event.setSurname(user.getSurname());
            event.setEmail(user.getEmail());
            event.setPersonalNumber(user.getPersonalNumber());

            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(Topic, event.getPersonalNumber(), eventJson);

        } catch (Exception e){
            throw  new RuntimeException("Ошибка отправки сообщения", e);
        }
    }
}
