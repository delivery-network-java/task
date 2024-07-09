package org.tyrell.task.application.services;

import org.tyrell.task.domain.dtos.UserRequestDto;
import org.tyrell.task.domain.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();

    UserResponseDto findById(Long id);

    UserResponseDto persist(UserRequestDto userRequestDto);

    UserResponseDto update(Long id, UserRequestDto userRequestDto);

    void delete(Long id);
}
