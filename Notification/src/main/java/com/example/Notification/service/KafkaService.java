package com.example.Notification.service;

import com.example.Notification.dto.NotificationCreatedEvent;
import com.example.Notification.model.Notification;
import com.example.Notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaService {

    @Autowired
    private NotificationRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "create_notification", groupId =  "notification-service-group")
    public void message(String message){

        try {
            NotificationCreatedEvent event = objectMapper.readValue(message, NotificationCreatedEvent.class);

            System.out.println("Получено событие о создании задачи: " + event.getTitle());

            Notification notification = new Notification();

            notification.setTitle(event.getTitle());
            notification.setDeadline(event.getDeadline());
            notification.setCreatorId(event.getCreatorId());
            notification.setAssigneeId(event.getAssigneeId());

            repository.save(notification);

            System.out.println("Локальная копия задачи " + event.getTitle() + " сохранена в базу данных.");
        } catch (Exception e){
            System.err.println("Ошибка обработки сообщения: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "update_notification", groupId = "notification-service-group") // для delete тоже сделать
    public void messageUpdateTask(String message){

        try {
            NotificationCreatedEvent event = objectMapper.readValue(message, NotificationCreatedEvent.class);

            System.out.println("Получено событие о обновлении задачи: " + event.getTitle());

            Notification notification = repository.findByTitle(event.getTitle()).orElseThrow(() ->
                    new RuntimeException("Уведомление не найдено!"));

            notification.setTitle(event.getTitle());
            notification.setDeadline(event.getDeadline());
            notification.setCreatorId(event.getCreatorId());
            notification.setAssigneeId(event.getAssigneeId());
            notification.setUpdatedAt(event.getUpdatedAt());

            repository.save(notification);

        } catch (Exception e){
            System.err.println("Ошибка обработки сообщения: " + e.getMessage());
        }
    }

    @KafkaListener(topics = "delete_notification", groupId = "notification-service-group")
    public void messageDeleteTask(String message){

        try {
            String title = objectMapper.readValue(message, String.class);

            System.out.println("Получен запрос на удаление уведомления: " + title);

            Notification notification = repository.findByTitle(title).orElse(null);

            if (notification != null) {
                repository.delete(notification);
                System.out.println("Уведомление удалено: " + title);
            }
            else {
                System.out.println("Уведомление не найдено: " + title);
            }

        } catch (Exception e){
            System.out.println("Ошибка обработки сообщения: " + e.getMessage());
        }
    }
}

