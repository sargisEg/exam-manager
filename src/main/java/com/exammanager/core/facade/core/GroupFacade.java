package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubgroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import org.springframework.data.web.PagedModel;

import java.util.List;

public interface GroupFacade {

    GroupDto createGroup(UserInfo userInfo, String departmentId, CreateGroupRequestDto dto);

    GroupDto getGroup(UserInfo userInfo, String departmentId, String groupId);

    PagedModel<GroupDto> getAllGroups(UserInfo userInfo, String departmentId, int page, int size);

    SubgroupDto createSubgroup(UserInfo userInfo, String departmentId, String groupId, CreateSubgroupRequestDto dto);

    PagedModel<SubgroupDto> getSubgroupsPage(UserInfo userInfo, String departmentId, String groupId, int page, int size);

    void deleteSubgroup(UserInfo userInfo, String departmentId, String groupId, String subgroupId);

    void deleteGroup(UserInfo userInfo, String departmentId, String groupId);

    SubgroupDto getSubgroup(UserInfo userInfo, String departmentId, String groupId, String subgroupId);

    List<SubgroupDto> getSubgroups(UserInfo userInfo, String departmentId, String groupId);
}
