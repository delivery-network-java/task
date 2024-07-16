package org.tyrell.task.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", schema = "user_app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", nullable = false)
    private Long id;

    @Column(name = "usr_username", length = 100, unique = true, nullable = false)
    private String username;

    @Column(name = "usr_password", length = 300, nullable = false)
    private String password;

    @Column(name = "usr_document", nullable = false)
    private Integer document;

    @Column(name = "usr_document_type", nullable = false)
    private Integer documentType;

    @Column(name = "usr_first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "usr_last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "usr_email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "usr_phone", length = 100, unique = true, nullable = false)
    private String phone;

    @Column(name = "usr_status", length = 100, nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "user")
    private List<RecurringTask> recurringTasks;

    @OneToMany(mappedBy = "user")
    private List<DeadlineTask> deadlineTasks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "usr_id"),
            inverseJoinColumns = @JoinColumn(name = "roe_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"usr_id", "roe_id"})
    )
    private List<Role> roles;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
