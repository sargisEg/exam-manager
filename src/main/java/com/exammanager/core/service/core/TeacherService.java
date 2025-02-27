package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.model.params.CreateTeacherParams;
import org.springframework.data.domain.Page;

public interface TeacherService {

    Teacher create(CreateTeacherParams params);
    Page<Teacher> findAll(int page, int size);

}
