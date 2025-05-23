package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateSubgroupParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SubgroupService {

    Subgroup create(CreateSubgroupParams params);

    Page<Subgroup> findByGroupId(String groupId, int page, int size);

    void deleteByIdAndGroupId(String subgroupId, String groupId);

    Optional<Subgroup> findByIdAndGroupId(String subgroupId, String groupId);

    Optional<Subgroup> findById(String subgroupId);

    List<Subgroup> findByGroupId(String groupId);
}
