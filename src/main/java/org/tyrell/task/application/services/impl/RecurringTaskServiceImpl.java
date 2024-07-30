package org.tyrell.task.application.services.impl;

import org.mapstruct.factory.Mappers;
import org.tyrell.task.application.mapper.RecurringTaskMapper;
import org.tyrell.task.application.services.RecurringTaskService;
import org.tyrell.task.domain.dtos.*;
import org.tyrell.task.domain.entities.RecurringTask;
import org.tyrell.task.persistence.repositories.RecurringTaskRepository;
import org.tyrell.task.persistence.repositories.Repository;

import java.util.List;

public class RecurringTaskServiceImpl implements RecurringTaskService {

    private final RecurringTaskMapper recurringTaskMapper;
    private final Repository<RecurringTask> repository;

    public RecurringTaskServiceImpl() {
        recurringTaskMapper = Mappers.getMapper(RecurringTaskMapper.class);
        repository = new RecurringTaskRepository();
    }

    @Override
    public List<RecurringTaskResponseDto> findAll() {
        return repository.findAll().stream().map(recurringTaskMapper::toResponseDto).toList();
    }

    @Override
    public RecurringTaskResponseDto findById(Long id) {
        var role = repository.findById(id);

        return recurringTaskMapper.toResponseDto(role);
    }

    @Override
    public RecurringTaskResponseDto persist(RecurringTaskRequestDto recurringTaskRequestDto) {
        var recurringTas = recurringTaskMapper.toEntity( recurringTaskRequestDto);
        var recurringTasPersisted = repository.persist(recurringTas);

        return recurringTaskMapper.toResponseDto(recurringTasPersisted);
    }


    @Override
    public RecurringTaskResponseDto update(Long id, RecurringTaskRequestDto recurringTaskRequestDto) {
        var recurringTask = repository.findById(id);

        if (null == recurringTask) {
            return null;
        }

        recurringTaskMapper.update(recurringTask, recurringTaskRequestDto);
        var recurringTaskUpdated = repository.update(recurringTask);

        return recurringTaskMapper.toResponseDto(recurringTaskUpdated);
    }
    


    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

}
