package com.exammanager.core.model.params;

import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseParams {

    private String name;
    private Group group;
    private Teacher teacher;
}
