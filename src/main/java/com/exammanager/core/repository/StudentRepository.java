package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Student;
import com.exammanager.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentRepository extends UserRepository<Student> {
    Page<Student> findAllBySubgroupGroupDepartmentId(String departmentId, Pageable pageable);

    Page<Student> findAllBySubgroupId(String subgroupId, Pageable pageable);

    Page<Student> findAllBySubgroupGroupId(String groupId, Pageable pageable);

    List<Student> findAllBySubgroupGroupDepartmentId(String departmentId);

    List<Student> findAllBySubgroupId(String subgroupId);

    List<Student> findAllBySubgroupGroupId(String groupId);

    List<Student> findBySubgroupId(String subgroupId);

}
