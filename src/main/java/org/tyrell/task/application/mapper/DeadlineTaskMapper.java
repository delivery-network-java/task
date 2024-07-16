package org.tyrell.task.application.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.tyrell.task.domain.dtos.DeadlineTaskRequestDto;
import org.tyrell.task.domain.dtos.DeadlineTaskResponseDto;
import org.tyrell.task.domain.entities.DeadlineTask;
import org.tyrell.task.domain.entities.User;
import org.tyrell.task.persistence.repositories.UserRepository;

@Mapper(componentModel = "spring")
public abstract class DeadlineTaskMapper {

    protected final UserRepository userRepository;

    protected DeadlineTaskMapper() {
        this.userRepository = new UserRepository();
    }

    @Mapping(target = "userId", source = "user.id")
    public abstract DeadlineTaskResponseDto toResponseDto(DeadlineTask deadlineTask);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    public abstract DeadlineTask toEntity(DeadlineTaskRequestDto deadlineTask);

    @InheritConfiguration(name = "toEntity")
    public abstract void update(@MappingTarget DeadlineTask deadlineTask, DeadlineTaskRequestDto deadlineTaskRequestDto);

    @AfterMapping
    void setUser(@MappingTarget DeadlineTask deadlineTask, DeadlineTaskRequestDto deadlineTaskRequestDto) {
        User user = userRepository.findById(deadlineTaskRequestDto.getUserId());
        deadlineTask.setUser(user);
    }

}
