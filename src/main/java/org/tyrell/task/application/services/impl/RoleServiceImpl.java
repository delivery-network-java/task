package org.tyrell.task.application.services.impl;

import org.mapstruct.factory.Mappers;
import org.tyrell.task.application.mapper.RoleMapper;
import org.tyrell.task.application.services.RoleService;
import org.tyrell.task.domain.dtos.RoleRequestDto;
import org.tyrell.task.domain.dtos.RoleResponseDto;
import org.tyrell.task.domain.entities.Role;
import org.tyrell.task.persistence.repositories.Repository;
import org.tyrell.task.persistence.repositories.RoleRepository;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final Repository<Role> repository;

    public RoleServiceImpl() {
        roleMapper = Mappers.getMapper(RoleMapper.class);
        repository = new RoleRepository();
    }

    @Override
    public List<RoleResponseDto> findAll() {
        return repository.findAll().stream().map(roleMapper::toResponseDto).toList();
    }

    @Override
    public RoleResponseDto findById(Long id) {
        var role = repository.findById(id);

        return roleMapper.toResponseDto(role);
    }

    @Override
    public RoleResponseDto persist(RoleRequestDto roleRequestDto) {
        var role = roleMapper.toEntity(roleRequestDto);
        var rolePersisted = repository.persist(role);

        return roleMapper.toResponseDto(rolePersisted);
    }

    @Override
    public RoleResponseDto update(Long id, RoleRequestDto roleRequestDto) {
        var role = repository.findById(id);

        if (null == role) {
            return null;
        }

        roleMapper.update(role, roleRequestDto);
        var roleUpdated = repository.update(role);

        return roleMapper.toResponseDto(roleUpdated);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

}
