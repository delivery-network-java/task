package org.tyrell.task.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recurring_tasks", schema = "task")
public class RecurringTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rtk_id", nullable = false)
    private Long id;

    @Column(name = "rtk_title", length = 100, nullable = false)
    private String title;

    @Column(name = "rtk_description", length = 500)
    private String description;

    @Column(name = "rtk_status", nullable = false)
    private Integer status;

    @Column(name = "rtk_frequency", length = 100, nullable = false)
    private String frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    public RecurringTask(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecurringTask that = (RecurringTask) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
