package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.params.CreateDepartmentParams;
import com.exammanager.core.service.core.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public Department create(CreateDepartmentParams map) {
        return null;
    }

    @Override
    public Optional<Department> findById(String departmentId) {
        return Optional.empty();
    }

    @Override
    public Department deleteById(String departmentId) {
        return null;
    }

    @Override
    public Page<Department> getAll(String keyword, int page, int size) {
        return null;
    }
}
