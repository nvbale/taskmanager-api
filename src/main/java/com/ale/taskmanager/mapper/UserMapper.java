package com.ale.taskmanager.mapper;

import com.ale.taskmanager.dto.response.UserResponseDTO;
import com.ale.taskmanager.entity.User;

public class UserMapper {
    public static UserResponseDTO toDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
