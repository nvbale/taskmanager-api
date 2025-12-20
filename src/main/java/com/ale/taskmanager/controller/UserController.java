package com.ale.taskmanager.controller;

import com.ale.taskmanager.dto.request.UserRequestDTO;
import com.ale.taskmanager.dto.response.UserResponseDTO;
import com.ale.taskmanager.entity.User;
import com.ale.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public UserResponseDTO create(@Valid @RequestBody UserRequestDTO request) {
        return userService.create(request);
    }
}
