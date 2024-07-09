package org.tyrell.task.domain.dtos;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class DeadlineTaskDto implements Serializable {

    private String title;

    private String description;

    private Long userId;

}
