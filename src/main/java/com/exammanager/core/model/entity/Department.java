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

    public Department(Long createdAt, Long updated, String name, String nameShort) {
        super(createdAt, updated);
        this.name = name;
        this.nameShort = nameShort;
    }
}
