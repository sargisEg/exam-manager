package com.exammanager.user.service.core;

import com.exammanager.user.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupService extends JpaRepository<Group, String> {
}
