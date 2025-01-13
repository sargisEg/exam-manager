package com.exammanager.core.controller;

import com.exammanager.core.model.dto.request.CreateExamRequestDto;
import com.exammanager.core.model.dto.request.UpdateExamRequestDto;
import com.exammanager.core.model.dto.response.ExamDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups/{groupId}/exams")
public class ExamController {


    @PostMapping
    ResponseEntity<ExamDto> createExam(@Valid @RequestBody CreateExamRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("{examId}")
    ResponseEntity<ExamDto> updateExam(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("examId") String examId,
            @RequestBody UpdateExamRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{examId}")
    ResponseEntity<ExamDto> getExam(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId, @PathVariable("examId") String examId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<Page<ExamDto>> getAllExamsInGroup(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{examId}")
    ResponseEntity<Void> deleteExam(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId, @PathVariable("examId") String examId) {
        return ResponseEntity.ok().build();
    }
}
