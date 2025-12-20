package com.ale.taskmanager.service;

import com.ale.taskmanager.dto.request.UserRequestDTO;
import com.ale.taskmanager.dto.response.UserResponseDTO;
import com.ale.taskmanager.entity.User;
import com.ale.taskmanager.mapper.UserMapper;
import com.ale.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserResponseDTO create(UserRequestDTO request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        return UserMapper.toDTO(userRepository.save(user));
    }
}
