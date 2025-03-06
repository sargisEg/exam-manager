package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.GradeExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import com.exammanager.core.model.enums.ExamStatus;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface ExamFacade {

    ExamDto createExam(UserInfo userInfo, String departmentId, String groupId, CreateExamRequestDto dto);

    ExamDto updateExam(UserInfo userInfo, String departmentId, String groupId, String examId, UpdateExamRequestDto dto);

    ExamDto getExam(UserInfo userInfo, String departmentId, String groupId, String examId);

    PagedModel<ExamDto> getExams(UserInfo userInfo, String departmentId, String groupId, int page, int size);

    void deleteExam(UserInfo userInfo, String departmentId, String groupId, String examId);

    PagedModel<ExamDto> getExamsByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size);

    PagedModel<ExamDto> getExamsBySubgroupId(UserInfo userInfo, String departmentId, String groupId, String subgroupId, ExamStatus status, int page, int size);

    PagedModel<ExamDto> getExamsByCourseId(UserInfo userInfo, String departmentId, String groupId, String courseId, ExamStatus status, int page, int size);

    List<ExamDto> getExamsMe(UserInfo userInfo, ExamStatus status);

    ExamDto gradeExam(UserInfo userInfo, String departmentId, String groupId, String examId, GradeExamRequestDto dto);
}
