package com.ale.taskmanager.controller;

import com.ale.taskmanager.dto.request.TaskRequestDTO;
import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/user/{userId}")
    public TaskResponseDTO create(@PathVariable Long userId, @Valid @RequestBody TaskRequestDTO request) {
       return taskService.create(request, userId);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping
    public List<TaskResponseDTO> getAll() {
        return taskService.getAll();
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@RequestBody @Valid TaskRequestDTO request, @PathVariable Long id) {
        return taskService.update(id, request);
    }

    @PutMapping("/{id}/complete")
    public TaskResponseDTO complete(@PathVariable Long id) {
        return taskService.complete(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
