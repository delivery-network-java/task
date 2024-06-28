package org.tyrell.task.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "deadline_tasks", schema = "task")
public class DeadlineTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dtk_id", nullable = false)
    private Long id;

    @Column(name = "dtk_title", length = 100, nullable = false)
    private String title;

    @Column(name = "dtk_description", length = 500)
    private String description;

    @Column(name = "dtk_status", nullable = false)
    private Integer status;

    @Column(name = "dtk_end_date", nullable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    public DeadlineTask(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeadlineTask that = (DeadlineTask) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
