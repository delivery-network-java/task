package org.tyrell.task.application.services.impl;

import org.mapstruct.factory.Mappers;
import org.tyrell.task.application.mapper.RoleMapper;
import org.tyrell.task.application.mapper.UserMapper;
import org.tyrell.task.application.services.UserService;
import org.tyrell.task.domain.dtos.UserRequestDto;
import org.tyrell.task.domain.dtos.UserResponseDto;
import org.tyrell.task.domain.entities.User;
import org.tyrell.task.persistence.repositories.Repository;
import org.tyrell.task.persistence.repositories.RoleRepository;
import org.tyrell.task.persistence.repositories.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final Repository<User> repository;

    public UserServiceImpl() {
        this.userMapper = Mappers.getMapper(UserMapper.class);;
        this.repository = new UserRepository();
    }
    @Override
    public List<UserResponseDto> findAll() {
        return repository.findAll().stream().map(userMapper::toResponseDto).toList();
    }

    @Override
    public UserResponseDto findById(Long id) {
        var role = repository.findById(id);

        return userMapper.toResponseDto(role);
    }

    @Override
    public UserResponseDto persist(UserRequestDto userRequestDto) {
        var user = userMapper.toEntity(userRequestDto);
        var rolePersisted = repository.persist(user);

        return userMapper.toResponseDto(rolePersisted);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        var user = repository.findById(id);

        if (null == user) {
            return null;
        }

        userMapper.update(user, userRequestDto);
        var roleUpdated = repository.update(user);

        return userMapper.toResponseDto(roleUpdated);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
