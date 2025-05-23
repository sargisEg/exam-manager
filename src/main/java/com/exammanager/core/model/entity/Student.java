package com.exammanager.core.model.entity;

import com.exammanager.user.model.entity.User;
import com.exammanager.user.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(Role.Of.STUDENT)
public class Student extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subgroup_id", foreignKey = @ForeignKey(name = "FK_STUDENTS_SUBGROUP_ID_SUBGROUPS_ID"))
    private Subgroup subgroup;

    public Student(Long createdAt, Long updated, String fullName, String email, String phone, String password, Role role, Subgroup subgroup) {
        super(createdAt, updated, fullName, email, phone, role, password);
        this.subgroup = subgroup;
    }

}
