package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.GroupMapper;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateSubgroupParams;
import com.exammanager.core.repository.SubGroupRepository;
import com.exammanager.core.service.core.SubgroupService;
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
public class SubgroupServiceImpl implements SubgroupService {

    private final SubGroupRepository subGroupRepository;
    private final GroupMapper groupMapper;
    private final UuidProvider uuidProvider;

    @Override
    public Subgroup create(CreateSubgroupParams params) {
        log.trace("Creating subgroup with params - {}", params);

        Subgroup subgroupFromParams = groupMapper.map(params);
        subgroupFromParams.setId(uuidProvider.getUuid());
        final Subgroup subgroup = subGroupRepository.save(subgroupFromParams);

        log.trace("Successfully created subgroup with params - {}, result - {}", params, subgroup);
        return subgroup;
    }

    @Override
    public Page<Subgroup> findByGroupId(String groupId, int page, int size) {
        log.trace("Finding subgroups page by group id - {}", groupId);

        final Page<Subgroup> subgroups = subGroupRepository.findByGroupId(groupId, PageRequest.of(page, size));

        log.trace("Successfully found subgroups page by group id - {}, result - {}", groupId, subgroups);
        return subgroups;
    }

    @Override
    public void deleteByIdAndGroupId(String subgroupId, String groupId) {

    }

    @Override
    public Optional<Subgroup> findByIdAndGroupId(String subgroupId, String groupId) {
        log.trace("Finding subgroup by id - {} and group id - {}", subgroupId, groupId);

        Optional<Subgroup> subgroup = subGroupRepository.findByIdAndGroupId(subgroupId, groupId);

        log.trace("Successfully found subgroup by id - {} and group id - {}, result - {}", subgroupId, groupId, subgroup);
        return subgroup;
    }

    @Override
    public Optional<Subgroup> findById(String subgroupId) {
        log.trace("Finding subgroup by id - {}", subgroupId);

        Optional<Subgroup> subgroup = subGroupRepository.findById(subgroupId);

        log.trace("Successfully found subgroup by id - {}, result - {}", subgroupId, subgroup);
        return subgroup;
    }

    @Override
    public List<Subgroup> findByGroupId(String groupId) {
        log.trace("Finding subgroups by group id - {}", groupId);

        List<Subgroup> subgroups = subGroupRepository.findByGroupId(groupId);

        log.trace("Successfully found subgroups by group id - {}, result - {}", groupId, subgroups);
        return subgroups;
    }
}
