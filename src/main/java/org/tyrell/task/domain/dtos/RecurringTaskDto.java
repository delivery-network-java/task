package org.tyrell.task.domain.dtos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tyrell.task.domain.entities.User;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class RecurringTaskDto implements Serializable {


    protected String title;

    protected String description;

    protected Integer status;

    protected String frequency;

    protected User user;


}
