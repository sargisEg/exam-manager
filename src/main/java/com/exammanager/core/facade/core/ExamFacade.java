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

    ExamDto createExam(UserInfo userInfo, CreateExamRequestDto dto);

    List<ExamDto> getUpcomingExamsByCourseId(UserInfo userInfo, String courseId);

    List<ExamDto> getNotGradedExamsByCourseId(UserInfo userInfo, String courseId);

    ExamDto updateExam(UserInfo userInfo, String examId, UpdateExamRequestDto dto);

    void deleteExam(UserInfo userInfo, String examId);

    List<ExamDto> getAllUpcomingExams(UserInfo userInfo);

    ExamDto getExam(UserInfo userInfo, String examId);

    ExamDto gradeExam(UserInfo userInfo, String examId, GradeExamRequestDto dto);



    List<ExamDto> getExams(UserInfo userInfo, String groupId);

    PagedModel<ExamDto> getExamsByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size);

    PagedModel<ExamDto> getExamsBySubgroupId(UserInfo userInfo, String departmentId, String groupId, String subgroupId, ExamStatus status, int page, int size);

    List<ExamDto> getExamsMe(UserInfo userInfo, ExamStatus status);

}
