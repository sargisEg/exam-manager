package com.exammanager.core.model.entity;

import com.exammanager.common.model.entity.BaseEntity;
import com.exammanager.core.model.enums.ExamStatus;
import com.exammanager.core.model.enums.ExamType;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXAMS")
public class Exam extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EXAMS_COURSE_ID_COURSES_ID"))
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subgroup_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EXAMS_SUBGROUP_ID_SUBGROUPS_ID"))
    private Subgroup subgroup;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "start_date", nullable = false)
    private Long startDate;

    @Column(name = "end_date", nullable = false)
    private Long endDate;

    @Column(name = "max_points", nullable = false)
    private Long maxPoints;

    @Column(name = "is_graded", nullable = false)
    private Boolean isGraded;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ExamStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ExamType type;
}
