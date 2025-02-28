package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateSubgroupParams;
import org.springframework.data.domain.Page;

public interface SubgroupService {

    Subgroup create(CreateSubgroupParams params);

    Page<Subgroup> findByGroupId(String groupId, int page, int size);

    void deleteByIdAndGroupId(String subgroupId, String groupId);
}
