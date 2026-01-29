package com.example.Task.service;

import com.example.Task.dto.DeleteNotification;
import com.example.Task.dto.NotificationCreatedEvent;
import com.example.Task.model.Task;
import com.example.Task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

@Service
public class KafkaService {

    private static final String TOPIC_CREATE = "create_notification";
    private static final String TOPIC_UPDATE = "update_notification";
    private static final String TOPIC_DELETE = "delete_notification";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TaskRepository repository;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Task task){
        try{
            NotificationCreatedEvent event = new NotificationCreatedEvent();
            event.setTitle(task.getTitle());
            event.setDeadline(task.getDeadline());
            event.setCreatorId(task.getCreatorId());
            event.setAssigneeId(task.getAssigneeId());


            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC_CREATE, event.getTitle(), eventJson);

        } catch (Exception e){
            throw  new RuntimeException("Ошибка отправки сообщения ", e);
        }
    }

    // подумать как сделать так чтобы изменялось только UpdatedAt
    public void sendMessageUpdateTask(Task task){
        try{
            NotificationCreatedEvent event = new NotificationCreatedEvent();
            event.setTitle(task.getTitle());
            event.setDeadline(task.getDeadline());
            event.setCreatorId(task.getCreatorId());
            event.setAssigneeId(task.getAssigneeId());
            event.setUpdatedAt(task.getUpdatedAt());

            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC_UPDATE, event.getTitle(), eventJson);

        } catch (Exception e){
            throw  new RuntimeException("Ошибка отправки сообщения ", e);
        }
    }

    public void sendMessageDeleteTask(String title){
        try{
            System.out.println("Отправка запроса на удаление уведомления: " + title);

            String eventJson = objectMapper.writeValueAsString(title);
            kafkaTemplate.send(TOPIC_DELETE, title, eventJson);

        } catch (Exception e){
            System.out.println("Ошибка отправки сообщения: " + e.getMessage());
        }
    }
}
