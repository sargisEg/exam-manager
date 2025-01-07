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
public class SubGroup extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false, foreignKey = @ForeignKey(name = "FK_SUBGROUPS_GROUP_ID_GROUPS_ID"))
    private Group group;

    public SubGroup(String id, Long createdAt, Long updated, Group group) {
        super(id, createdAt, updated);
        this.group = group;
    }
}
