package com.ale.taskmanager.controller;

import com.ale.taskmanager.dto.request.UserRequestDTO;
import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.dto.response.UserResponseDTO;
import com.ale.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO create(@Valid @RequestBody UserRequestDTO request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Long id, @Valid @RequestBody UserRequestDTO request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskResponseDTO> tasks(@PathVariable Long id) {
        return userService.getUserTasks(id);
    }

}
