package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.SubGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubGroupRepository extends JpaRepository<SubGroup, String> {
}
