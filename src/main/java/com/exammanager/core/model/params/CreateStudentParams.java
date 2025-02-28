package com.exammanager.core.model.params;

import com.exammanager.core.model.entity.Subgroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentParams {

    private String fullName;
    private String email;
    private String phone;
    private String password;
    private Subgroup subgroup;
}
