package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.params.CreateDepartmentParams;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DepartmentService {

    Department create(CreateDepartmentParams map);

    Optional<Department> findById(String departmentId);

    Department deleteById(String departmentId);

    Page<Department> getAll(String keyword, int page, int size);
}
