package org.tyrell.task.application.services;


import org.tyrell.task.domain.dtos.RecurringTaskDto;
import org.tyrell.task.domain.dtos.RecurringTaskRequestDto;
import org.tyrell.task.domain.dtos.RecurringTaskResponseDto;

import java.util.List;

public interface RecurringTaskService {
    List<RecurringTaskResponseDto> findAll();

    RecurringTaskDto findById(Long id);

    RecurringTaskResponseDto persist(RecurringTaskRequestDto recurringTaskRequestDto);

    RecurringTaskResponseDto update(Long id, RecurringTaskRequestDto recurringTaskRequestDto);

    void delete(Long id);

}
