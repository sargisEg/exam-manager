package com.exammanager.user.service.core;

import com.exammanager.user.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentService extends JpaRepository<Department, String> {
}
