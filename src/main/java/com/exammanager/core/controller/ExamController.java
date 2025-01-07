package com.exammanager.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups/{groupId}/exams")
public class ExamController {

//    @PostMapping
//    ResponseEntity<ExamDto> createExam(@RequestBody CreateExamRequestDto dto) {
//
//    }
}
