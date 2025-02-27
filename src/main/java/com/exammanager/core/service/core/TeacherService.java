package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Teacher;
import org.springframework.data.domain.Page;

public interface TeacherService {

    Page<Teacher> findAll(int page, int size);

}
