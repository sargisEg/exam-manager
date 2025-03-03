package com.exammanager.core.model.dto.response;

import com.exammanager.user.model.dto.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private UserDto user;
    private List<CourseDto> courses;
}
