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
@Table(name = "SUBGROUPS")
public class Subgroup extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SUBGROUPS_GROUP_ID_GROUPS_ID"))
    private Group group;

    public Subgroup(Long createdAt, Long updated, String name, Group group) {
        super(createdAt, updated);
        this.name = name;
        this.group = group;
    }
}
