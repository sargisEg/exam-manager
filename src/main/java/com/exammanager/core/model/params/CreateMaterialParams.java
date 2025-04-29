package com.exammanager.core.model.params;

import com.exammanager.core.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialParams {

    private String name;
    private String location;
    private String extension;
    private Course course;
    private long size;
}
