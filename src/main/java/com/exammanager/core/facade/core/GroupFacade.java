package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;

import java.util.List;

public interface GroupFacade {

    List<GroupDto> getAllGroups(UserInfo userInfo);


    GroupDto createGroup(UserInfo userInfo, CreateGroupRequestDto dto);

    GroupDto getGroup(UserInfo userInfo, String groupId);

    SubgroupDto createSubgroup(UserInfo userInfo, String groupId);

    void deleteSubgroup(UserInfo userInfo, String groupId, String subgroupId);

    void deleteGroup(UserInfo userInfo, String groupId);

    List<SubgroupDto> getSubgroups(UserInfo userInfo, String groupId);


    //Teacher

    List<GroupDto> getGroupsByTeacherId(UserInfo userInfo);

    GroupDto getGroupByIdAndTeacherId(UserInfo userInfo, String groupId);

    List<SubgroupDto> getSubgroupsByTeacherId(UserInfo userInfo, String groupId);
}
