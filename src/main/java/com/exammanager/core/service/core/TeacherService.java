package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Teacher;
import com.exammanager.core.model.params.CreateTeacherParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher create(CreateTeacherParams params);

    Page<Teacher> findAll(int page, int size);

    Optional<Teacher> findById(String teacherId);

    List<Teacher> findAll();

    void deleteById(String teacherId);
}
