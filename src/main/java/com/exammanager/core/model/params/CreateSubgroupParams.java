package com.exammanager.core.model.params;

import com.exammanager.core.model.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubgroupParams {

    private String name;
    private Group group;
}
