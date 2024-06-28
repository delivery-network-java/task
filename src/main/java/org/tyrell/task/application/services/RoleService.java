package org.tyrell.task.application.services;

import org.tyrell.task.domain.dtos.RoleRequestDto;
import org.tyrell.task.domain.dtos.RoleResponseDto;

import java.util.List;

public interface RoleService {
    List<RoleResponseDto> findAll();

    RoleResponseDto findById(Long id);

    RoleResponseDto persist(RoleRequestDto roleRequestDto);

    RoleResponseDto update(Long id, RoleRequestDto roleRequestDto);

    void delete(Long id);

}
