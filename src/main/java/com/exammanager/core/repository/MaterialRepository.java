package com.exammanager.core.repository;

import com.exammanager.core.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {

    List<Material> findByCourseId(String courseId);

    void deleteByCourseId(String courseId);

    Optional<Material> findByIdAndCourseId(String materialId, String courseId);
}
