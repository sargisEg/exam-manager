package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.DepartmentMapper;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.params.CreateDepartmentParams;
import com.exammanager.core.repository.DepartmentRepository;
import com.exammanager.core.service.core.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final UuidProvider uuidProvider;

    @Override
    public Department create(CreateDepartmentParams params) {
        log.trace("Creating department with params - {}", params);
        final Department departmentFromParams = departmentMapper.map(params);
        departmentFromParams.setId(uuidProvider.getUuid());
        final Department department = departmentRepository.save(departmentFromParams);
        log.trace("Successfully created department with params - {}, result - {}", params, department);
        return department;
    }

    @Override
    public Optional<Department> findById(String departmentId) {
        log.trace("Finding department with id - {}", departmentId);
        final Optional<Department> department = departmentRepository.findById(departmentId);
        log.trace("Successfully found department with id - {}, result - {}", department, department);
        return department;
    }

    @Override
    public void deleteById(String departmentId) {
        log.trace("Deleting department with id - {}", departmentId);
        departmentRepository.deleteById(departmentId);
        log.trace("Successfully deleted department with id - {}", departmentId);
    }

    @Override
    public Page<Department> findAll(int page, int size) {
        log.trace("Getting all departments");

        final Page<Department> departments = departmentRepository.findAll(PageRequest.of(page, size));

        log.trace("Successfully got all departments, result - {}", departments);
        return departments;
    }
}
