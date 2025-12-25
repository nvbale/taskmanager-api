package com.ale.taskmanager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @PostMapping(path = "login")
    public String login() {
        return "login successful";
    }

    @PostMapping(path = "register")
    public String register() {
        return "register successful";
    }

}
