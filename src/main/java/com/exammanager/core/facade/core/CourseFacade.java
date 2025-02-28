package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateCourseRequestDto;
import com.exammanager.core.model.dto.response.CourseDto;
import org.springframework.data.web.PagedModel;

public interface CourseFacade {

    CourseDto createCourse(UserInfo userInfo, String departmentId, String groupId, CreateCourseRequestDto dto);

    CourseDto getCourse(UserInfo userInfo, String departmentId, String groupId, String courseId);

    PagedModel<CourseDto> getCourses(UserInfo userInfo, String departmentId, String groupId, int page, int size);

    void deleteCourse(UserInfo userInfo, String departmentId, String groupId, String courseId);
}
