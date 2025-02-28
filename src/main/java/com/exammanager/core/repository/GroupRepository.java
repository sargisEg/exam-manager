package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {


    Page<Group> findByDepartmentId(String departmentId, Pageable pageable);

    Optional<Group> findByIdAndDepartmentId(String groupId, String departmentId);
}
