package com.ale.taskmanager.service;

import com.ale.taskmanager.dto.request.UserRequestDTO;
import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.dto.response.UserResponseDTO;
import com.ale.taskmanager.entity.Task;
import com.ale.taskmanager.entity.User;
import com.ale.taskmanager.mapper.TaskMapper;
import com.ale.taskmanager.mapper.UserMapper;
import com.ale.taskmanager.repository.TaskRepository;
import com.ale.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository,
                       TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public UserResponseDTO create(UserRequestDTO request) {
        return UserMapper.toDTO(userRepository.save(UserMapper.toEntity(request)));
    }

    public UserResponseDTO getById(Long id) {
        return UserMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found")));
    }

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserResponseDTO update(Long id, UserRequestDTO userRequestUpdated) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

        user.setEmail(userRequestUpdated.getEmail());
        user.setName(userRequestUpdated.getName());

        return UserMapper.toDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<TaskResponseDTO> getUserTasks(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(TaskMapper::toDTO).toList();
    }

}
