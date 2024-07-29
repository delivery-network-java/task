package org.tyrell.task.application.services.impl;

import org.mapstruct.factory.Mappers;
import org.tyrell.task.application.mapper.RoleMapper;
import org.tyrell.task.application.services.RoleService;
import org.tyrell.task.domain.dtos.RoleRequestDto;
import org.tyrell.task.domain.dtos.RoleResponseDto;
import org.tyrell.task.domain.entities.Role;
import org.tyrell.task.persistence.DBConnection;
import org.tyrell.task.persistence.repositories.Repository;
import org.tyrell.task.persistence.repositories.RoleRepository;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final Repository<Role> repository;

    public RoleServiceImpl() {
        roleMapper = Mappers.getMapper(RoleMapper.class);
        repository = new RoleRepository();
    }

    @Override
    public List<RoleResponseDto> findAll() throws SQLException {
        try (var connection = DBConnection.getConnection()) {
            repository.setConnection(connection);

            return repository.findAll().stream().map(roleMapper::toResponseDto).toList();
        }
    }

    @Override
    public RoleResponseDto findById(Long id) throws SQLException {
        try (var connection = DBConnection.getConnection()) {
            repository.setConnection(connection);
            var role = repository.findById(id);

            return roleMapper.toResponseDto(role);
        }
    }

    @Override
    public RoleResponseDto persist(RoleRequestDto roleRequestDto) throws SQLException {
        try (var connection = DBConnection.getConnection()) {
            repository.setConnection(connection);

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            try {
                var role = roleMapper.toEntity(roleRequestDto);
                var rolePersisted = repository.persist(role);
                connection.commit();

                return roleMapper.toResponseDto(rolePersisted);
            } catch (Exception e) {
                connection.rollback();

                throw e;
            }
        }
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto roleRequestDto) throws SQLException {
        try (var connection = DBConnection.getConnection()) {
            repository.setConnection(connection);
            var role = repository.findById(id);

            if (null == role) {
                return null;
            }

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            try {
                roleMapper.update(role, roleRequestDto);
                var roleUpdated = repository.update(role);
                connection.commit();

                return roleMapper.toResponseDto(roleUpdated);
            } catch (Exception e) {
                connection.rollback();

                throw e;
            }
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (var connection = DBConnection.getConnection()) {
            repository.setConnection(connection);

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }

            try {
                repository.delete(id);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();

                throw e;
            }
        }
    }

}
