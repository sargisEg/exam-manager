package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.params.CreateGroupParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group create(CreateGroupParams params);

    Optional<Group> findById(String groupId);

    Page<Group> findAll(int page, int size);

    List<Group> findAll();

    void deleteById(String groupId);
}
