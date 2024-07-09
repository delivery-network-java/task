package org.tyrell.task.domain.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tyrell.task.domain.entities.User;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class DeadlineTaskResponseDto extends DeadlineTaskDto {

    private Long id;

    private Integer status;

    private Date endDate;

}
