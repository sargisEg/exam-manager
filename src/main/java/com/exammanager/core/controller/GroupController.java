package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.GroupFacade;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/groups")
@SuppressWarnings("unused")
public class GroupController {

    private final GroupFacade groupFacade;


    @GetMapping()
    @Secured("ROLE_TEACHER")
    ResponseEntity<List<GroupDto>> getAllGroups() {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getGroupsByTeacherId(userInfo), HttpStatus.OK);
    }

    @GetMapping("{groupId}")
    @Secured("ROLE_TEACHER")
    ResponseEntity<GroupDto> getGroupByTeacherId(@PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getGroupByIdAndTeacherId(userInfo, groupId), HttpStatus.OK);
    }

    @GetMapping("{groupId}/subgroups")
    @Secured("ROLE_TEACHER")
    ResponseEntity<List<SubgroupDto>> getSubgroups(@PathVariable("groupId") String groupId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        return new ResponseEntity<>(groupFacade.getSubgroupsByTeacherId(userInfo, groupId), HttpStatus.OK);
    }
}
