package com.exammanager.core.model.params;

import com.exammanager.core.model.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupParams {

    private String name;
    private int startYear;
    private int endYear;
    private Department department;
}
