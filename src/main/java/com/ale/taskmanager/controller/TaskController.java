package com.ale.taskmanager.controller;

import com.ale.taskmanager.dto.request.TaskRequestDTO;
import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskResponseDTO create(@Valid @RequestBody TaskRequestDTO request) {
        return taskService.create(request);
    }

    @GetMapping(path = "/user/{userId}")
    public List<TaskResponseDTO> getByUser(@PathVariable Long userId){
        return taskService.findByUser(userId);
    }

    @PutMapping(path = "/{id}/complete")
    public void complete(@PathVariable Long id) {
        taskService.completeTask(id);
    }
}
