package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateDepartmentRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.params.CreateDepartmentParams;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapperImpl implements DepartmentMapper {
    @Override
    public CreateDepartmentParams map(CreateDepartmentRequestDto dto) {
        return new CreateDepartmentParams(
                dto.getName(),
                dto.getNameShort()
        );
    }

    @Override
    public DepartmentDto map(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getNameShort()
        );
    }

    @Override
    public Department map(CreateDepartmentParams params) {
        final Department department = new Department(
                params.getName(),
                params.getNameShort()
        );
        department.setCreatedAt(System.currentTimeMillis());
        department.setUpdatedAt(System.currentTimeMillis());
        return department;
    }
}
