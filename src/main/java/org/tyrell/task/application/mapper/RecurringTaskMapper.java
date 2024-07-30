package org.tyrell.task.application.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.tyrell.task.domain.dtos.RecurringTaskRequestDto;
import org.tyrell.task.domain.dtos.RecurringTaskResponseDto;

import org.tyrell.task.domain.entities.RecurringTask;

@Mapper //(componentModel = "spring")
public interface RecurringTaskMapper {

    RecurringTaskResponseDto toResponseDto(RecurringTask recurringTask);

    @Mapping(target = "id", ignore = true)
    RecurringTask toEntity(RecurringTaskRequestDto recurringTaskRequestDto);

    @InheritConfiguration(name = "toEntity")
    void update(@MappingTarget RecurringTask recurringTask, RecurringTaskRequestDto recurringTaskRequestDto);

}
