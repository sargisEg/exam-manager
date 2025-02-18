package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ExamFacadeImpl implements ExamFacade {
    @Override
    public ExamDto createExam(UserInfo userInfo, String departmentId, String groupId, CreateExamRequestDto dto) {
        return null;
    }

    @Override
    public ExamDto updateExam(UserInfo userInfo, String departmentId, String groupId, String examId) {
        return null;
    }

    @Override
    public ExamDto getExam(UserInfo userInfo, String departmentId, String groupId, String examId) {
        return null;
    }

    @Override
    public Page<ExamDto> getExams(UserInfo userInfo, String departmentId, String groupId, String keyword, int page, int size) {
        return null;
    }

    @Override
    public void deleteExam(UserInfo userInfo, String departmentId, String groupId, String examId) {

    }
}
