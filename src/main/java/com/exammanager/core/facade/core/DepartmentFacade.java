package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.request.CreateDepartmentRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import org.springframework.data.web.PagedModel;

public interface DepartmentFacade {

    DepartmentDto createDepartment(UserInfo userInfo, CreateDepartmentRequestDto dto);

    DepartmentDto getDepartment(UserInfo userInfo, String departmentId);

    void deleteDepartment(UserInfo userInfo, String departmentId);

    PagedModel<DepartmentDto> getAllDepartment(UserInfo userInfo, int page, int size);

}
