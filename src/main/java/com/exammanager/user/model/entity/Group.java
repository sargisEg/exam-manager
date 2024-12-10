package com.exammanager.user.model.entity;

import com.exammanager.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GROUPS")
public class Group extends BaseEntity {

    @Column(name = "start_year", nullable = false)
    private Integer startYear;

    @Column(name = "end_year", nullable = false)
    private Integer endYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(name = "FK_GROUPS_DEPARTMENT_ID_DEPARTMENTS_ID"))
    private Department department;

    public Group(String id, Long createdAt, Long updated, Integer startYear, Integer endYear, Department department) {
        super(id, createdAt, updated);
        this.startYear = startYear;
        this.endYear = endYear;
        this.department = department;
    }
}
