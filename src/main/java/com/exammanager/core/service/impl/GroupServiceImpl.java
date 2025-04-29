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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
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
    public Optional<Group> findById(String groupId) {
        log.trace("Finding group by id - {}", groupId);

        final Optional<Group> group = groupRepository.findById(groupId);

        log.trace("Successfully found group by id - {}, result - {}", groupId, group);
        return group;
    }

    @Override
    public Page<Group> findAll(int page, int size) {
        log.trace("Finding all groups page");

        final Page<Group> groups = groupRepository.findAll(PageRequest.of(page, size));

        log.trace("Successfully found all groups page, result - {}", groups);
        return groups;
    }

    @Override
    public List<Group> findAll() {
        log.trace("Finding all groups");

        final List<Group> groups = groupRepository.findAll();

        log.trace("Successfully found all groups, result - {}", groups);
        return groups;
    }

    @Override
    public void deleteById(String groupId) {

    }
}
