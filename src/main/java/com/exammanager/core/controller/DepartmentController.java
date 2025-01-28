package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.DepartmentFacade;
import com.exammanager.core.model.dto.request.CreateDepartmentRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments")
public class DepartmentController {

    private final DepartmentFacade departmentFacade;

    @PostMapping
    ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody CreateDepartmentRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(departmentFacade.createDepartment(userInfo, dto), HttpStatus.OK);
    }

    @GetMapping("{departmentId}")
    ResponseEntity<DepartmentDto> getDepartment(@PathVariable("departmentId") String departmentId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(departmentFacade.getDepartment(userInfo, departmentId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<Page<DepartmentDto>> getAllDepartments(@RequestParam("keyword") String keyword, @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(departmentFacade.getAllDepartment(userInfo, keyword, page, size), HttpStatus.OK);
    }

    @DeleteMapping("{departmentId}")
    ResponseEntity<Void> deleteDepartment(@PathVariable("departmentId") String departmentId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        departmentFacade.deleteDepartment(userInfo, departmentId);
        return ResponseEntity.ok().build();
    }
}
