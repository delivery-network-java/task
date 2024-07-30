package org.tyrell.task.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tyrell.task.application.services.RecurringTaskService;
import org.tyrell.task.application.services.impl.RecurringTaskServiceImpl;
import org.tyrell.task.domain.dtos.RecurringTaskDto;
import org.tyrell.task.domain.dtos.RecurringTaskRequestDto;
import org.tyrell.task.domain.dtos.RecurringTaskResponseDto;
import org.tyrell.task.persistence.DBConnection;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/RecurringTask")
public class RecurringTaskController {

    private RecurringTaskService recurringTaskService;

    @PostConstruct
    public void init() {
        recurringTaskService = new RecurringTaskServiceImpl();

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
    public List<RecurringTaskResponseDto> getAllgetRecurringTask() {
        return recurringTaskService.findAll();
    }

    @GetMapping("/{id}")
    public RecurringTaskDto getRecurringTask(@PathVariable Long id) {
        var recurringTaskDto = recurringTaskService.findById(id);

        if (null == recurringTaskDto) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RecurringTask not found");
        }

        return recurringTaskDto;
    }

    @PostMapping
    public RecurringTaskDto creategetRecurringTask(@RequestBody RecurringTaskRequestDto recurringTaskRequestDto) {
        return recurringTaskService.persist(recurringTaskRequestDto);
    }

    @PutMapping("/{id}")
    public RecurringTaskDto updategetRecurringTask(@PathVariable Long id, @RequestBody RecurringTaskRequestDto recurringTaskRequestDto) {
        return recurringTaskService.update(id, recurringTaskRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletegetRecurringTask(@PathVariable Long id) {
        recurringTaskService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
