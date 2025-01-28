package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import org.springframework.data.domain.Page;

public interface ExamFacade {

    ExamDto createExam(UserInfo userInfo, String departmentId, String groupId, CreateExamRequestDto dto);

    ExamDto updateExam(UserInfo userInfo, String departmentId, String groupId, String examId);

    ExamDto getExam(UserInfo userInfo, String departmentId, String groupId, String examId);

    Page<ExamDto> getExams(UserInfo userInfo, String departmentId, String groupId, String keyword, int page, int size);

    void deleteExam(UserInfo userInfo, String departmentId, String groupId, String examId);
}
