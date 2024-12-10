package com.exammanager.user.model.entity;

import com.exammanager.user.model.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(Role.Of.TEACHER)
public class Teacher extends User {

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Group> groups;

    public Teacher(String id, Long createdAt, Long updated, String fullName, String email, String phone, String password, Set<Group> groups) {
        super(id, createdAt, updated, fullName, email, phone, password);
        this.groups = groups;
    }
}
