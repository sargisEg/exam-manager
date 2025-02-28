package com.exammanager.core.model.dto.response;

import com.exammanager.user.model.dto.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String id;
    private String name;
    private GroupDto group;
    private UserDto teacher;
}
