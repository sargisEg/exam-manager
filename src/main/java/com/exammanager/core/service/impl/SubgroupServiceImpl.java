package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateSubgroupParams;
import com.exammanager.core.service.core.SubgroupService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class SubgroupServiceImpl implements SubgroupService {
    @Override
    public Subgroup create(CreateSubgroupParams map) {
        return null;
    }

    @Override
    public Page<Subgroup> findByGroupId(String groupId, String keyword, int page, int size) {
        return null;
    }

    @Override
    public void deleteByIdAndGroupId(String subgroupId, String groupId) {

    }
}
