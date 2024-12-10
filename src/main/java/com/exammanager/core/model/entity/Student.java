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
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "FK_STUDENTS_GROUP_ID_GROUPS_ID"))
    private Group group;

    public Student(String id, Long createdAt, Long updated, String fullName, String email, String phone, String password, Group group) {
        super(id, createdAt, updated, fullName, email, phone, password);
        this.group = group;
    }

}
