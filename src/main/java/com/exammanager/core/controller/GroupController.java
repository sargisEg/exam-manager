package com.exammanager.core.controller;

import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubGroupRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubGroupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups")
public class GroupController {

    @PostMapping
    ResponseEntity<GroupDto> createGroup(@Valid @RequestBody CreateGroupRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{groupId}")
    ResponseEntity<DepartmentDto> getDepartment(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<Page<GroupDto>> getAllGroupsInDepartment(@PathVariable("departmentId") String departmentId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("{groupId}/subgroups")
    ResponseEntity<SubGroupDto> createSubGroup(@PathVariable("groupId") String groupId, @Valid @RequestBody CreateSubGroupRequestDto dto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{groupId}/subgroups")
    ResponseEntity<Page<SubGroupDto>> getSubGroups(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}/subgroups/{subgroupId}")
    ResponseEntity<Void> deleteSubGroup(@PathVariable("groupId") String groupId, @PathVariable("subgroupId") String subgroupId) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}")
    ResponseEntity<Void> deleteGroup(@PathVariable("groupId") String groupId) {
        return ResponseEntity.ok().build();
    }
}
