package com.exammanager.core.model.entity;

import com.exammanager.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTMENTS")
public class Department extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_short", nullable = false)
    private String nameShort;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_of_department", nullable = false, foreignKey = @ForeignKey(name = "DEPARTMENTS_HEAD_OF_DEPARTMENT_TEACHERS_ID"))
    private Teacher headOfDepartment;

    public Department(String id, Long createdAt, Long updated, String name, String nameShort, Teacher headOfDepartment) {
        super(id, createdAt, updated);
        this.name = name;
        this.nameShort = nameShort;
        this.headOfDepartment = headOfDepartment;
    }
}
