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
public class UserDto implements Serializable {
        protected Integer document;
        protected Integer documentType;
        protected String username;
        protected String password;
        protected String email;
        protected String firstName;
        protected String lastName;
        protected String phone;
        protected Integer status;
}
