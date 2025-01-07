package com.exammanager.core.model.entity;

import com.exammanager.user.model.entity.User;
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
    private Set<SubGroup> subGroups;

    public Teacher(String id, Long createdAt, Long updated, String fullName, String email, String phone, String password, Set<SubGroup> subGroups) {
        super(id, createdAt, updated, fullName, email, phone, password);
        this.subGroups = subGroups;
    }
}
