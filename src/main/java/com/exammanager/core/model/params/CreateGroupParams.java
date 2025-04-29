package com.exammanager.core.model.params;

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
}
