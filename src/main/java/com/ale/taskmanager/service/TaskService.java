package com.ale.taskmanager.service;

import com.ale.taskmanager.dto.request.TaskRequestDTO;
import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.entity.Task;
import com.ale.taskmanager.entity.User;
import com.ale.taskmanager.mapper.TaskMapper;
import com.ale.taskmanager.repository.TaskRepository;
import com.ale.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskResponseDTO create(TaskRequestDTO taskRequestDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setCompleted(false);
        task.setUser(user);

        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public TaskResponseDTO getById(Long id) {
        return TaskMapper.toDTO(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found")));
    }

    public List<TaskResponseDTO> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO taskRequestDTO) {
        Task task =  taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskRequestDTO.getTitle());
        task.setCompleted(taskRequestDTO.isCompleted());
        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public TaskResponseDTO complete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task " + id + "not found"));
        task.setCompleted(true);
        return TaskMapper.toDTO(taskRepository.save(task));
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

}
