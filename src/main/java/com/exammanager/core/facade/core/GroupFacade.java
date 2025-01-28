package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubGroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import org.springframework.data.domain.Page;

public interface GroupFacade {

    GroupDto createGroup(UserInfo userInfo, String departmentId, CreateGroupRequestDto dto);

    GroupDto getGroup(UserInfo userInfo, String departmentId, String groupId);

    Page<GroupDto> getAllGroups(UserInfo userInfo, String departmentId, String keyword, int page, int size);

    SubgroupDto createSubgroup(UserInfo userInfo, String departmentId, String groupId, CreateSubGroupRequestDto dto);

    Page<SubgroupDto> getSubgroups(UserInfo userInfo, String departmentId, String groupId, String keyword, int page, int size);

    void deleteSubgroup(UserInfo userInfo, String departmentId, String groupId, String subgroupId);

    void deleteGroup(UserInfo userInfo, String departmentId, String groupId);
}
