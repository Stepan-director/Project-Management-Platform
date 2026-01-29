package com.example.Task.controller;


import com.example.Task.dto.CreateTaskRequest;
import com.example.Task.dto.UpdateTask;
import com.example.Task.model.Task;
import com.example.Task.repository.TaskRepository;
import com.example.Task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository repository;

    @PostMapping("/create")
    public ResponseEntity<?> createTAsk(@RequestAttribute("personalNumber") UUID creatorId, @RequestBody CreateTaskRequest request) {
        try {
            Task task = taskService.createTAsk(creatorId ,request);
            return ResponseEntity.ok("Задача успешно создана!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалось создать задачу! " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTask updateTask){
        try {
            Task task = taskService.updateTask(updateTask);
            return ResponseEntity.ok("Задача изменена!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалось внести изменения в задачу! " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestParam  String title){
        try{
            taskService.deleteTask(title);
            return ResponseEntity.ok("Задача удалена!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалить задачу! " + e.getMessage());
        }
    }
}
