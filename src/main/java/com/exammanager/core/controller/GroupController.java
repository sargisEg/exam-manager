package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.GroupFacade;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubGroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups")
public class GroupController {

    private final GroupFacade groupFacade;

    @PostMapping
    ResponseEntity<GroupDto> createGroup(@PathVariable("departmentId") String departmentId, @Valid @RequestBody CreateGroupRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.createGroup(userInfo, departmentId, dto), HttpStatus.OK);
    }

    @GetMapping("{groupId}")
    ResponseEntity<GroupDto> getGroup(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getGroup(userInfo, departmentId, groupId), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<PagedModel<GroupDto>> getAllGroupsInDepartment(
            @PathVariable("departmentId") String departmentId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getAllGroups(userInfo, departmentId, page, size), HttpStatus.OK);
    }

    @PostMapping("{groupId}/subgroups")
    ResponseEntity<SubgroupDto> createSubGroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @Valid @RequestBody CreateSubGroupRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.createSubgroup(userInfo, departmentId, groupId, dto), HttpStatus.OK);
    }

    @GetMapping("{groupId}/subgroups")
    ResponseEntity<PagedModel<SubgroupDto>> getSubGroups(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getSubgroups(userInfo, departmentId, groupId, page, size), HttpStatus.OK);
    }

    @DeleteMapping("{groupId}/subgroups/{subgroupId}")
    ResponseEntity<Void> deleteSubGroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("subgroupId") String subgroupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        groupFacade.deleteSubgroup(userInfo, departmentId, groupId, subgroupId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}")
    ResponseEntity<Void> deleteGroup(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        groupFacade.deleteGroup(userInfo, departmentId, groupId);
        return ResponseEntity.ok().build();
    }
}
