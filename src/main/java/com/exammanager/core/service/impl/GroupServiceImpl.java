package com.exammanager.core.service.impl;

import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.params.CreateGroupParams;
import com.exammanager.core.service.core.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GroupServiceImpl implements GroupService {
    @Override
    public Group create(CreateGroupParams map) {
        return null;
    }

    @Override
    public Optional<Group> findByIdAndDepartmentId(String groupId, String departmentId) {
        return Optional.empty();
    }

    @Override
    public Page<Group> findByDepartmentId(String departmentId, String keyword, int page, int size) {
        return null;
    }

    @Override
    public void deleteByIdAndDepartmentId(String groupId, String departmentId) {

    }
}
