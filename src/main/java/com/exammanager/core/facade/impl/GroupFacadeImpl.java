package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.GroupFacade;
import com.exammanager.core.mapper.GroupMapper;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubgroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.service.core.DepartmentService;
import com.exammanager.core.service.core.GroupService;
import com.exammanager.core.service.core.SubgroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupFacadeImpl implements GroupFacade {

    private final DepartmentService departmentService;
    private final GroupService groupService;
    private final SubgroupService subgroupService;

    private final GroupMapper groupMapper;

    @Override
    @Transactional
    public GroupDto createGroup(UserInfo userInfo, String departmentId, CreateGroupRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Crating group for provided request - {}, user - {}, department - {}", dto, userInfo.id(), departmentId);

        final Department department = getDepartmentById(departmentId);

        final GroupDto responseDto = groupMapper.map(groupService.create(groupMapper.map(department, dto)));

        log.debug("Successfully created group for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDto getGroup(UserInfo userInfo, String departmentId, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Getting group with id - {}, user - {}, department - {}", groupId, userInfo.id(), departmentId);

        getDepartmentById(departmentId);

        final GroupDto responseDto = groupMapper.map(getGroupById(groupId, departmentId));

        log.trace("Successfully got group with id - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<GroupDto> getAllGroups(UserInfo userInfo, String departmentId, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        log.trace("Getting groups in department - {}, user - {}", departmentId, userInfo.id());

        getDepartmentById(departmentId);

        final Page<GroupDto> p = groupService.findByDepartmentId(departmentId, page, size)
                .map(groupMapper::map);
        final PagedModel<GroupDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got groups in department - {}, response - {}", departmentId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public SubgroupDto createSubgroup(UserInfo userInfo, String departmentId, String groupId, CreateSubgroupRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Crating subgroup for provided request - {}, user - {}, department - {}, group - {}", dto, userInfo.id(), departmentId, groupId);

        getDepartmentById(departmentId);
        final Group group = getGroupById(groupId, departmentId);

        final SubgroupDto responseDto = groupMapper.map(subgroupService.create(groupMapper.map(group, dto)));

        log.debug("Successfully created subgroup for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<SubgroupDto> getSubgroupsPage(UserInfo userInfo, String departmentId, String groupId, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Getting subgroups page in group - {}, user - {}, department - {}", groupId, userInfo.id(), departmentId);

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final Page<SubgroupDto> p = subgroupService.findByGroupId(groupId, page, size)
                .map(groupMapper::map);
        final PagedModel<SubgroupDto> responseDto = new PagedModel<>(p);

        log.debug("Successfully got subgroups page in group - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteSubgroup(UserInfo userInfo, String departmentId, String groupId, String subgroupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(subgroupId, "subgroupId should not be null");
        log.debug("Deleting subgroup by id - {}, in group - {}, user - {}, department - {}", subgroupId, groupId, userInfo.id(), departmentId);

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        subgroupService.deleteByIdAndGroupId(subgroupId, groupId);

        log.debug("Successfully deleted subgroup by id - {}", subgroupId);
    }

    @Override
    @Transactional
    public void deleteGroup(UserInfo userInfo, String departmentId, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Deleting group by id - {}, in department - {}, user - {}", groupId, departmentId, userInfo.id());

        getDepartmentById(departmentId);

        groupService.deleteByIdAndDepartmentId(groupId, departmentId);

        log.debug("Successfully deleted group by id - {}", groupId);
    }

    @Override
    @Transactional(readOnly = true)
    public SubgroupDto getSubgroup(UserInfo userInfo, String departmentId, String groupId, String subgroupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(subgroupId, "subgroupId should not be null");
        log.trace("Getting subgroup with id - {}, user - {}, department - {}, group - {}", subgroupId, userInfo.id(), departmentId, groupId);

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final SubgroupDto responseDto = groupMapper.map(subgroupService.findByIdAndGroupId(subgroupId, groupId).orElseThrow(() -> new NotFoundException(
                        "Not found group with id - " + groupId + "and department id - " + departmentId,
                        "Not found group with id - " + groupId
                )));

        log.trace("Successfully got subgroup with id - {}, response - {}", subgroupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubgroupDto> getSubgroups(UserInfo userInfo, String departmentId, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Getting all subgroups in group - {}, user - {}, department - {}", groupId, userInfo.id(), departmentId);

        getDepartmentById(departmentId);
        getGroupById(groupId, departmentId);

        final List<SubgroupDto> responseDto = subgroupService.findByGroupId(groupId)
                .stream().map(groupMapper::map).toList();

        log.debug("Successfully got all subgroups in group - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    private Department getDepartmentById(String departmentId) {
        return departmentService.findById(departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found department with id - " + departmentId,
                        "Not found department with id - " + departmentId
                ));
    }

    private Group getGroupById(String groupId, String departmentId) {
        return groupService.findByIdAndDepartmentId(groupId, departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found group with id - " + groupId + "and department id - " + departmentId,
                        "Not found group with id - " + groupId
                ));
    }
}
