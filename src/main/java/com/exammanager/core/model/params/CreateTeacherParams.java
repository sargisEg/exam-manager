package com.exammanager.core.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeacherParams {

    private String fullName;
    private String email;
    private String phone;
    private String password;

}
