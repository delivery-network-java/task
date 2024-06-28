package org.tyrell.task.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles", schema = "user_app")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roe_id", nullable = false)
    private Long id;

    @Column(name = "roe_name", length = 30, unique = true, nullable = false)
    private String name;

}
