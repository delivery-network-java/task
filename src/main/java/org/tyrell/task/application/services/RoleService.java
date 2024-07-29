package org.tyrell.task.application.services;

import org.tyrell.task.domain.dtos.RoleRequestDto;
import org.tyrell.task.domain.dtos.RoleResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    List<RoleResponseDto> findAll() throws SQLException;

    RoleResponseDto findById(Long id) throws SQLException;

    RoleResponseDto persist(RoleRequestDto roleRequestDto) throws SQLException;

    RoleResponseDto update(Long id, RoleRequestDto roleRequestDto) throws SQLException;

    void delete(Long id) throws SQLException;

}
