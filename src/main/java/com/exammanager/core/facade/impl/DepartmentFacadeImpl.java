package com.exammanager.core.facade.impl;

import com.exammanager.common.exception.NotFoundException;
import com.exammanager.common.security.UserInfo;
import com.exammanager.core.facade.core.DepartmentFacade;
import com.exammanager.core.mapper.DepartmentMapper;
import com.exammanager.core.model.dto.request.CreateDepartmentRequestDto;
import com.exammanager.core.model.dto.response.DepartmentDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.service.core.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public DepartmentDto createDepartment(UserInfo userInfo, CreateDepartmentRequestDto dto) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.notNull(dto, "dto should not be null");
        log.debug("Creating department for provided request - {}, user - {}", dto, userInfo.id());

        DepartmentDto responseDto = departmentMapper.map(departmentService.create(departmentMapper.map(dto)));

        log.debug("Successfully created department for provided request - {}, response - {}", dto, responseDto);
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartment(UserInfo userInfo, String departmentId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        log.debug("Getting department for provided request with department id - {}, user - {}", departmentId, userInfo.id());

        final Department optionalDepartment = departmentService.findById(departmentId).orElseThrow(() ->
                new NotFoundException(
                        "Not found department with id - " + departmentId,
                        "Not found department with id - " + departmentId
                )
        );
        DepartmentDto responseDto = departmentMapper.map(optionalDepartment);

        log.trace("Successfully got department for provided request with department id - {}, department - {}", departmentId, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteDepartment(UserInfo userInfo, String departmentId) {
        Assert.notNull(userInfo, "userInfo should not be null");
        Assert.hasText(departmentId, "departmentId should not be null");
        log.debug("Deleting department for provided request with department id - {}, user - {}", departmentId, userInfo.id());

        departmentService.deleteById(departmentId);

        log.debug("Successfully deleted department for provided request with department id - {}", departmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public PagedModel<DepartmentDto> getAllDepartment(UserInfo userInfo, int page, int size) {
        Assert.notNull(userInfo, "userInfo should not be null");
        log.trace("Getting all departments for provided request, user - {}", userInfo.id());

        final Page<Department> departments = departmentService.findAll(page, size);
        final Page<DepartmentDto> p = departments
                .map(departmentMapper::map);
        PagedModel<DepartmentDto> responseDto = new PagedModel<>(p);

        log.trace("Successfully got all departments for provided request, departments - {}", responseDto);
        return responseDto;
    }
}
