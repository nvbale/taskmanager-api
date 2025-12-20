package com.ale.taskmanager.service;

import com.ale.taskmanager.dto.request.TaskRequestDTO;
import com.ale.taskmanager.dto.request.UserRequestDTO;
import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.entity.Task;
import com.ale.taskmanager.entity.User;
import com.ale.taskmanager.mapper.TaskMapper;
import com.ale.taskmanager.repository.TaskRepository;
import com.ale.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponseDTO create(TaskRequestDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setCompleted(false);
        task.setUser(user);

        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public List<TaskResponseDTO> findByUser(Long userId) {
        return taskRepository.findByUserId(userId)
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    public void completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        taskRepository.save(task);
    }
}
