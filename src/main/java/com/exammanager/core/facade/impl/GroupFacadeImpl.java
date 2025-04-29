package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.GroupFacade;
import com.exammanager.core.mapper.GroupMapper;
import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import com.exammanager.core.model.entity.Course;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateSubgroupParams;
import com.exammanager.core.service.core.CourseService;
import com.exammanager.core.service.core.GroupService;
import com.exammanager.core.service.core.SubgroupService;
import com.exammanager.utils.GroupUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;
    private final SubgroupService subgroupService;
    private final CourseService courseService;

    private final GroupMapper groupMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getAllGroups(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Finding all groups, user - {}", userInfo.id());

        final List<GroupDto> responseDto = groupService.findAll().stream()
                .map(groupMapper::map)
                .toList();
        log.trace("Successfully found all groups, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public GroupDto createGroup(UserInfo userInfo, CreateGroupRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Crating group for provided request - {}, user - {}", dto, userInfo.id());

        final GroupDto responseDto = groupMapper.map(groupService.create(groupMapper.map(dto)));

        log.debug("Successfully created group for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDto getGroup(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.trace("Finding group with id - {}, user - {}", groupId, userInfo.id());

        final GroupDto responseDto = groupMapper.map(getGroupById(groupId));

        log.trace("Successfully found group with id - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public SubgroupDto createSubgroup(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.debug("Crating subgroup for provided request, user - {}, group - {}", userInfo.id(), groupId);

        final Group group = getGroupById(groupId);

        final List<Subgroup> subgroups = subgroupService.findByGroupId(groupId);
        final SubgroupDto responseDto = groupMapper.map(subgroupService.create(new CreateSubgroupParams(
                group.getName() + "-" + (subgroups.size() + 1),
                group
        )));

        log.debug("Successfully created subgroup for provided request, response - {}", responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteSubgroup(UserInfo userInfo, String groupId, String subgroupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        Assert.hasText(subgroupId, "subgroupId should not be null");
        log.debug("Deleting subgroup by id - {}, in group - {}, user - {}", subgroupId, groupId, userInfo.id());

        getGroupById(groupId);
        subgroupService.findByIdAndGroupId(subgroupId, groupId)
                .ifPresent(subgroup -> subgroupService.deleteById(subgroupId));
        log.debug("Successfully deleted subgroup by id - {}", subgroupId);
    }

    @Override
    @Transactional
    public void deleteGroup(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Deleting group by id - {}, user - {}", groupId, userInfo.id());

        groupService.deleteById(groupId);

        log.debug("Successfully deleted group by id - {}", groupId);
    }


    @Override
    @Transactional(readOnly = true)
    public List<SubgroupDto> getSubgroups(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Finding all subgroups in group - {}, user - {}", groupId, userInfo.id());

        getGroupById(groupId);

        final List<SubgroupDto> responseDto = subgroupService.findByGroupId(groupId)
                .stream().map(groupMapper::map).toList();

        log.debug("Successfully found all subgroups in group - {}, response - {}", groupId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getGroupsByTeacherId(UserInfo userInfo) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.debug("Finding all groups for teacher with id - {} for provided request", userInfo.id());

        final List<GroupDto> responseDto = courseService.findByTeacherId(userInfo.id()).stream()
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(Course::getGroup)
                .distinct()
                .map(groupMapper::map)
                .toList();

        log.debug("Successfully found all groups for teacher with id - {} for provided request, response - {}", userInfo.id(), responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDto getGroupByIdAndTeacherId(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Finding group with id - {} for teacher with id - {} for provided request", groupId, userInfo.id());

        final GroupDto responseDto = courseService.findByTeacherId(userInfo.id()).stream()
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(Course::getGroup)
                .distinct()
                .filter(group -> group.getId().equals(groupId))
                .map(groupMapper::map)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Not found group with id - " + groupId,
                        "Not found group with id - " + groupId
                ));

        log.debug("Successfully found group with id - {} for teacher with id - {} for provided request, response - {}", groupId, userInfo.id(), responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubgroupDto> getSubgroupsByTeacherId(UserInfo userInfo, String groupId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(groupId, "groupId should not be null");
        log.debug("Finding all subgroups in group - {} for teacher with id - {} for provided request", groupId, userInfo.id());

        final Group group = courseService.findByTeacherId(userInfo.id()).stream()
                .filter(c -> c.getSemester().equals(GroupUtils.getGroupSemester(c.getGroup())))
                .map(Course::getGroup)
                .distinct()
                .filter(g -> g.getId().equals(groupId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Not found group with id - " + groupId,
                        "Not found group with id - " + groupId
                ));

        final List<SubgroupDto> responseDto = subgroupService.findByGroupId(group.getId()).stream()
                .map(groupMapper::map)
                .toList();

        log.debug("Successfully found all subgroups in group - {} for teacher with id - {} for provided request, response - {}", groupId, userInfo.id(), responseDto);
        return responseDto;
    }

    private Group getGroupById(String groupId) {
        return groupService.findById(groupId).orElseThrow(() ->
                new NotFoundException(
                        "Not found group with id - " + groupId,
                        "Not found group with id - " + groupId
                ));
    }
}
