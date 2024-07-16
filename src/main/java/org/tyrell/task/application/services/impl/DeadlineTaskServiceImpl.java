package org.tyrell.task.application.services.impl;

import org.mapstruct.factory.Mappers;
import org.tyrell.task.application.mapper.DeadlineTaskMapper;
import org.tyrell.task.application.services.DeadlineTaskService;
import org.tyrell.task.domain.dtos.DeadlineTaskRequestDto;
import org.tyrell.task.domain.dtos.DeadlineTaskResponseDto;
import org.tyrell.task.domain.entities.DeadlineTask;
import org.tyrell.task.persistence.repositories.DeadlineTaskRepository;
import org.tyrell.task.persistence.repositories.Repository;

import java.util.List;

public class DeadlineTaskServiceImpl implements DeadlineTaskService {

    private final DeadlineTaskMapper deadlineTaskMapper;
    private final Repository<DeadlineTask> repository;

    public DeadlineTaskServiceImpl() {
        deadlineTaskMapper = Mappers.getMapper(DeadlineTaskMapper.class);
        repository = new DeadlineTaskRepository();
    }

    @Override
    public List<DeadlineTaskResponseDto> findAll() {
        return repository.findAll().stream().map(deadlineTaskMapper::toResponseDto).toList();
    }

    @Override
    public DeadlineTaskResponseDto findById(Long id) {
        var deadlineTask = repository.findById(id);

        return deadlineTaskMapper.toResponseDto(deadlineTask);
    }

    @Override
    public DeadlineTaskResponseDto persist(DeadlineTaskRequestDto deadlineTaskRequestDto) {
        var deadLineTask = deadlineTaskMapper.toEntity(deadlineTaskRequestDto);
        var deadLineTaskPersisted = repository.persist(deadLineTask);

        return deadlineTaskMapper.toResponseDto(deadLineTaskPersisted);
    }

    @Override
    public DeadlineTaskResponseDto update(Long id, DeadlineTaskRequestDto deadlineTaskRequestDto) {
        var deadLineTask = repository.findById(id);

        if (null == deadLineTask) {
            return null;
        }

        deadlineTaskMapper.update(deadLineTask, deadlineTaskRequestDto);
        var deadLineTaskUpdated = repository.update(deadLineTask);

        return deadlineTaskMapper.toResponseDto(deadLineTaskUpdated);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

}
