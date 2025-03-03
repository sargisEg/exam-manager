package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.response.ExamResultDto;
import org.springframework.data.web.PagedModel;

public interface ExamResultFacade {

    PagedModel<ExamResultDto> getAllByGroupId(UserInfo userInfo, String departmentId, String groupId, int page, int size);

    PagedModel<ExamResultDto> getAllByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size);

    PagedModel<ExamResultDto> getAllBySubgroupId(UserInfo userInfo, String departmentId, String groupId, String subgroupId, int page, int size);

    PagedModel<ExamResultDto> getAllByCourseId(UserInfo userInfo, String departmentId, String groupId, String courseId, int page, int size);
}
