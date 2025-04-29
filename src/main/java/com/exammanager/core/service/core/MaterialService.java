package com.exammanager.core.service.core;

import com.exammanager.core.model.entity.Material;
import com.exammanager.core.model.params.CreateMaterialParams;

import java.util.List;
import java.util.Optional;

public interface MaterialService {

    Material create(CreateMaterialParams params);

    List<Material> findByCourseId(String courseId);

    void deleteById(String id);

    void deleteByCourseId(String courseId);

    Optional<Material> findById(String materialId);

    Optional<Material> findByIdAndCourseId(String materialId, String courseId);
}
