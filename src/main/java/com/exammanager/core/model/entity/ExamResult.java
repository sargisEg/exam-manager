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
@Table(name = "EXAM_RESULTS")
public class ExamResult extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_EXAM_RESULTS_STUDENT_ID_USERS_ID"))
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id", foreignKey = @ForeignKey(name = "FK_EXAM_RESULTS_EXAM_ID_EXAMS_ID"))
    private Exam exam;

    @Column(name = "point", nullable = false)
    private Integer point;
}
