package org.tyrell.task.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tyrell.task.application.services.impl.DeadlineTaskServiceImpl;
import org.tyrell.task.domain.dtos.DeadlineTaskRequestDto;
import org.tyrell.task.domain.dtos.DeadlineTaskResponseDto;
import org.tyrell.task.persistence.DBConnection;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/deadline-task")
public class DeadlineTaskController {

    private DeadlineTaskServiceImpl deadlineTaskService;

    @PostConstruct
    public void init() {
        deadlineTaskService = new DeadlineTaskServiceImpl();

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
    public List<DeadlineTaskResponseDto> getAllDeadlineTask() {
        return deadlineTaskService.findAll();
    }

    @GetMapping("/{id}")
    public DeadlineTaskResponseDto getDeadlineTask(@PathVariable Long id) {
        var deadlineTaskResponseDto = deadlineTaskService.findById(id);

        if (null == deadlineTaskResponseDto) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DeadlineTask not found");
        }

        return deadlineTaskResponseDto;
    }

    @PostMapping
    public DeadlineTaskResponseDto createDeadlineTask(@RequestBody DeadlineTaskRequestDto deadlineTaskRequestDto) {
        return deadlineTaskService.persist(deadlineTaskRequestDto);
    }

    @PutMapping("/{id}")
    public DeadlineTaskResponseDto updateRole(@PathVariable Long id, @RequestBody DeadlineTaskRequestDto deadlineTaskRequestDto) {
        return deadlineTaskService.update(id, deadlineTaskRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        deadlineTaskService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
