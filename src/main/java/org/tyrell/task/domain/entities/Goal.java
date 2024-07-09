package org.tyrell.task.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "goals", schema = "task")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gol_id", nullable = false)
    private Long id;

    @Column(name = "gol_name", length = 30, nullable = false)
    private String name;

    @Column(name = "gol_description", length = 200)
    private String description;

}
