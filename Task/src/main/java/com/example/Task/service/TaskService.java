package com.example.Task.service;

import com.example.Task.dto.CreateTaskRequest;
import com.example.Task.dto.UpdateTask;
import com.example.Task.model.Task;
import com.example.Task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private KafkaService kafkaService;

    public Task createTAsk(UUID creatorId ,CreateTaskRequest request){
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCreatorId(creatorId);
        task.setAssigneeId(request.getAssigneeId());
        task.setDeadline(request.getDeadline());
        task.setPriority(request.getPriority());

        Task saved = repository.save(task);

        kafkaService.sendMessage(saved);

        return saved;
    }

    public Task updateTask(UpdateTask updateTask){
        Task task = repository.findByTitle(updateTask.getTitle()).orElseThrow(
                () -> new RuntimeException("Задача не найдена!")
        );

        task.setTitle(updateTask.getTitle());
        task.setDescription(updateTask.getDescription());
        task.setStatus(updateTask.getStatus());
        task.setAssigneeId(updateTask.getAssigneeId());
        task.setPriority(updateTask.getPriority());

        task.setUpdatedAt(LocalDateTime.now());

        Task saved = repository.save(task);

        kafkaService.sendMessageUpdateTask(saved);

        return saved;
    }

    @Transactional
    public void deleteTask(String title){
        Task task = repository.findByTitle(title).orElseThrow(
                () -> new RuntimeException("Задача не найдена!")
        );

        kafkaService.sendMessageDeleteTask(title);

        repository.delete(task);
    }
}
