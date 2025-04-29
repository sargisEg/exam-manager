package com.exammanager.core.controller.admin;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.GroupFacade;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/admin/groups")
@SuppressWarnings("unused")
@Secured("ROLE_ADMIN")
public class AdminGroupController {

    private final GroupFacade groupFacade;

    @PostMapping
    ResponseEntity<GroupDto> createGroup(@Valid @RequestBody CreateGroupRequestDto dto) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.createGroup(userInfo, dto), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<GroupDto>> getAllGroups() {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getAllGroups(userInfo), HttpStatus.OK);
    }

    @GetMapping("{groupId}")
    ResponseEntity<GroupDto> getGroup(@PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getGroup(userInfo, groupId), HttpStatus.OK);
    }

    @GetMapping("{groupId}/subgroups")
    ResponseEntity<List<SubgroupDto>> getSubgroups(
            @PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getSubgroups(userInfo, groupId), HttpStatus.OK);
    }

    @PostMapping("{groupId}/subgroups")
    ResponseEntity<SubgroupDto> createSubgroup(@PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.createSubgroup(userInfo, groupId), HttpStatus.OK);
    }

    @DeleteMapping("{groupId}/subgroups/{subgroupId}")
    ResponseEntity<Void> deleteSubgroup(@PathVariable("groupId") String groupId, @PathVariable("subgroupId") String subgroupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        groupFacade.deleteSubgroup(userInfo, groupId, subgroupId);
        return ResponseEntity.ok().build();
    }
}
