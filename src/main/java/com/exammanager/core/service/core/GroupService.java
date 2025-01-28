package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.params.CreateGroupParams;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface GroupService {

    Group create(CreateGroupParams map);

    Optional<Group> findByIdAndDepartmentId(String groupId, String departmentId);

    Page<Group> findByDepartmentId(String departmentId, String keyword, int page, int size);

    void deleteByIdAndDepartmentId(String groupId, String departmentId);
}
