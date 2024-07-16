package org.tyrell.task.application.services;

import org.tyrell.task.domain.dtos.DeadlineTaskRequestDto;
import org.tyrell.task.domain.dtos.DeadlineTaskResponseDto;

import java.util.List;

public interface DeadlineTaskService {
    List<DeadlineTaskResponseDto> findAll();

    DeadlineTaskResponseDto findById(Long id);

    DeadlineTaskResponseDto persist(DeadlineTaskRequestDto deadLineTaskRequestDto);

    DeadlineTaskResponseDto update(Long id, DeadlineTaskRequestDto deadlineTaskRequestDto);

    void delete(Long id);

}
