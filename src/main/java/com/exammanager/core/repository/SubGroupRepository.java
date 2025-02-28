package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Subgroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubGroupRepository extends JpaRepository<Subgroup, String> {

    Page<Subgroup> findByGroupId(String groupId, Pageable pageable);

    Optional<Subgroup> findByIdAndGroupId(String subgroupId, String groupId);

    List<Subgroup> findByGroupId(String groupId);
}
