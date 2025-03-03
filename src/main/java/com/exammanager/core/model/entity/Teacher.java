package com.exammanager.core.model.entity;

import com.exammanager.user.model.entity.User;
import com.exammanager.user.model.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@DiscriminatorValue(Role.Of.TEACHER)
public class Teacher extends User {

    public Teacher(Long createdAt, Long updated, String fullName, String email, String phone, String password, Role role) {
        super(createdAt, updated, fullName, email, phone, role, password);
    }
}
