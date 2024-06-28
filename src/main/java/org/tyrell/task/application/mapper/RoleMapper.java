package org.tyrell.task.application.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.tyrell.task.domain.dtos.RoleRequestDto;
import org.tyrell.task.domain.dtos.RoleResponseDto;
import org.tyrell.task.domain.entities.Role;

@Mapper //(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDto toResponseDto(Role role);

    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleRequestDto roleRequestDto);

    @InheritConfiguration(name = "toEntity")
    void update(@MappingTarget Role role, RoleRequestDto roleRequestDto);

}
