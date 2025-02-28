package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.GroupMapper;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.params.CreateGroupParams;
import com.exammanager.core.repository.GroupRepository;
import com.exammanager.core.service.core.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UuidProvider uuidProvider;

    @Override
    public Group create(CreateGroupParams params) {
        log.trace("Creating group with params - {}", params);
        Group groupFromParams = groupMapper.map(params);
        groupFromParams.setId(uuidProvider.getUuid());
        final Group group = groupRepository.save(groupFromParams);

        log.trace("Creating group with params - {}, result - {}", params, group);
        return group;
    }

    @Override
    public Optional<Group> findByIdAndDepartmentId(String groupId, String departmentId) {
        log.trace("Finding group by id - {} and department id - {}", groupId, departmentId);

        final Optional<Group> group = groupRepository.findByIdAndDepartmentId(groupId, departmentId);

        log.trace("Successfully found group by id - {} and department id - {}, result - {}", group, departmentId, group);
        return group;
    }

    @Override
    public Page<Group> findByDepartmentId(String departmentId, int page, int size) {
        log.trace("Finding groups by department id - {}", departmentId);

        final Page<Group> groups = groupRepository.findByDepartmentId(departmentId, PageRequest.of(page, size));

        log.trace("Successfully found groups by department id - {}, result - {}", departmentId, groups);
        return groups;
    }

    @Override
    public void deleteByIdAndDepartmentId(String groupId, String departmentId) {

    }
}
