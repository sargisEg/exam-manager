package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.GroupFacade;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubgroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/departments/{departmentId}/groups")
@SuppressWarnings("unused")
public class GroupController {

    private final GroupFacade groupFacade;

    @PostMapping
    @Secured("ROLE_ADMIN")
    ResponseEntity<GroupDto> createGroup(@PathVariable("departmentId") String departmentId, @Valid @RequestBody CreateGroupRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.createGroup(userInfo, departmentId, dto), HttpStatus.OK);
    }

    @GetMapping("{groupId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<GroupDto> getGroup(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getGroup(userInfo, departmentId, groupId), HttpStatus.OK);
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    ResponseEntity<PagedModel<GroupDto>> getAllGroupsInDepartment(
            @PathVariable("departmentId") String departmentId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getAllGroups(userInfo, departmentId, page, size), HttpStatus.OK);
    }

    @PostMapping("{groupId}/subgroups")
    @Secured("ROLE_ADMIN")
    ResponseEntity<SubgroupDto> createSubgroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @Valid @RequestBody CreateSubgroupRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.createSubgroup(userInfo, departmentId, groupId, dto), HttpStatus.OK);
    }

    @GetMapping("{groupId}/subgroups/page")
    @Secured("ROLE_ADMIN")
    ResponseEntity<PagedModel<SubgroupDto>> getSubgroupsPage(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getSubgroupsPage(userInfo, departmentId, groupId, page, size), HttpStatus.OK);
    }

    @GetMapping("{groupId}/subgroups")
    @Secured("ROLE_ADMIN")
    ResponseEntity<List<SubgroupDto>> getSubgroups(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getSubgroups(userInfo, departmentId, groupId), HttpStatus.OK);
    }

    @GetMapping("{groupId}/subgroups/{subgroupId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<SubgroupDto> getSubgroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("subgroupId") String subgroupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getSubgroup(userInfo, departmentId, groupId, subgroupId), HttpStatus.OK);
    }

    @DeleteMapping("{groupId}/subgroups/{subgroupId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<Void> deleteSubgroup(
            @PathVariable("departmentId") String departmentId,
            @PathVariable("groupId") String groupId,
            @PathVariable("subgroupId") String subgroupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        groupFacade.deleteSubgroup(userInfo, departmentId, groupId, subgroupId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{groupId}")
    @Secured("ROLE_ADMIN")
    ResponseEntity<Void> deleteGroup(@PathVariable("departmentId") String departmentId, @PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        groupFacade.deleteGroup(userInfo, departmentId, groupId);
        return ResponseEntity.ok().build();
    }
}
