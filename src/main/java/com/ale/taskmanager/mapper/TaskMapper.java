package com.ale.taskmanager.mapper;

import com.ale.taskmanager.dto.response.TaskResponseDTO;
import com.ale.taskmanager.entity.Task;

public class TaskMapper {
    public static TaskResponseDTO toDTO(Task task){
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setCompleted(task.isCompleted());
        dto.setUserId(task.getUser().getId());
        return dto;
    }
}
