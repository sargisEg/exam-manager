package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import org.springframework.data.web.PagedModel;

public interface ExamFacade {

    ExamDto createExam(UserInfo userInfo, String departmentId, String groupId, CreateExamRequestDto dto);

    ExamDto updateExam(UserInfo userInfo, String departmentId, String groupId, String examId, UpdateExamRequestDto dto);

    ExamDto getExam(UserInfo userInfo, String departmentId, String groupId, String examId);

    PagedModel<ExamDto> getExams(UserInfo userInfo, String departmentId, String groupId, int page, int size);

    void deleteExam(UserInfo userInfo, String departmentId, String groupId, String examId);

    PagedModel<ExamDto> getExamsByStudentId(UserInfo userInfo, String departmentId, String groupId, String studentId, int page, int size);
}
