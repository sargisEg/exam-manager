package com.exammanager.core.controller;

import com.exammanager.core.model.dto.request.CreateDepartmentRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments")
public class DepartmentController {

    @PostMapping
    ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody CreateDepartmentRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{departmentId}")
    ResponseEntity<DepartmentDto> getDepartment(@PathVariable("departmentId") String departmentId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<Page<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{departmentId}")
    ResponseEntity<Void> deleteDepartment() {
        return ResponseEntity.ok().build();
    }
}
