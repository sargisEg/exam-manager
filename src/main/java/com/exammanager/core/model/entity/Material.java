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
@Table(name = "MATERIALS")
public class Material extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "FK_MATERIALS_COURSE_ID_COURSES_ID"))
    private Course course;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "size", nullable = false)
    private Long size;

    public Material(Long createdAt, Long updatedAt, String name, Course course, String location, String extension, Long size) {
        super(createdAt, updatedAt);
        this.name = name;
        this.course = course;
        this.location = location;
        this.extension = extension;
        this.size = size;
    }
}
