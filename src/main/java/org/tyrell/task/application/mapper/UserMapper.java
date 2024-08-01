package org.tyrell.task.application.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.tyrell.task.domain.dtos.UserRequestDto;
import org.tyrell.task.domain.dtos.UserResponseDto;
import org.tyrell.task.domain.entities.User;

@Mapper //(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto userRequestDto);

    @InheritConfiguration(name = "toEntity")
    void update(@MappingTarget User user, UserRequestDto userRequestDto);

}
