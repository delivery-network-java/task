package org.tyrell.task.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tyrell.task.application.services.RoleService;
import org.tyrell.task.application.services.impl.RoleServiceImpl;
import org.tyrell.task.domain.dtos.RoleRequestDto;
import org.tyrell.task.domain.dtos.RoleResponseDto;
import org.tyrell.task.persistence.DBConnection;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @PostConstruct
    public void init() {
        roleService = new RoleServiceImpl();

        try {
            DBConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            DBConnection.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<RoleResponseDto> getAllRoles() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleResponseDto getRole(@PathVariable Long id) {
        var roleResponseDto = roleService.findById(id);

        if (null == roleResponseDto) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }

        return roleResponseDto;
    }

    @PostMapping
    public RoleResponseDto createRole(@RequestBody RoleRequestDto roleRequestDto) {
        return roleService.persist(roleRequestDto);
    }

    @PutMapping("/{id}")
    public RoleResponseDto updateRole(@PathVariable Long id, @RequestBody RoleRequestDto roleRequestDto) {
        return roleService.update(id, roleRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
