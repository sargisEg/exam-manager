package com.exammanager.core.service.core;

import com.exammanager.core.model.dto.request.StudentRequestFilter;
import com.exammanager.core.model.entity.Student;
import com.exammanager.core.model.params.CreateStudentParams;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student create(CreateStudentParams params);

    Page<Student> findAll(StudentRequestFilter filter, int page, int size);

    List<Student> findAll(StudentRequestFilter filter);

    Optional<Student> findById(String studentId);

    List<Student> findBySubgroupId(String subgroupId);

    List<Student> findByGroupId(String groupId);

    void deleteById(String studentId);
}
