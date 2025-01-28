package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateDepartmentRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.params.CreateDepartmentParams;

public interface DepartmentMapper {

    CreateDepartmentParams map(CreateDepartmentRequestDto dto);

    DepartmentDto map(Department department);
}
