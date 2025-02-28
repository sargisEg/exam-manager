package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Subgroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubGroupRepository extends JpaRepository<Subgroup, String> {

    Page<Subgroup> findByGroupId(String groupId, Pageable pageable);
}
