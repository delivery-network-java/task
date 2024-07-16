package org.tyrell.task.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class DeadlineTaskResponseDto extends DeadlineTaskDto {

    private Long id;

    private Integer status;

    private Date endDate;

}
