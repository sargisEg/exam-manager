package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;

import java.util.List;

public interface CourseFacade {

    //Admin
    CourseDto createCourse(UserInfo userInfo, CreateCourseRequestDto dto);

    List<CourseDto> getCoursesByGroupId(UserInfo userInfo, String groupId);

    void deleteCourse(UserInfo userInfo, String courseId);

    List<CourseDto> getCoursesByTeacherId(UserInfo userInfo, String teacherId);


    //Teacher
    List<CourseDto> getCoursesByGroupIdAndTeacherId(UserInfo userInfo, String groupId);

    CourseDto getCourseByIdAndTeacherId(UserInfo userInfo, String courseId);
}
