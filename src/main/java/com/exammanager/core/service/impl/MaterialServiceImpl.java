package com.exammanager.core.service.impl;

import com.exammanager.common.service.UuidProvider;
import com.exammanager.core.mapper.MaterialMapper;
import com.exammanager.core.model.entity.Material;
import com.exammanager.core.model.params.CreateMaterialParams;
import com.exammanager.core.repository.MaterialRepository;
import com.exammanager.core.service.core.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final UuidProvider uuidProvider;
    private final MaterialMapper materialMapper;

    @Override
    public Material create(CreateMaterialParams params) {
        Assert.notNull(params, "params should not be null");
        log.trace("Creating material with params - {}", params);

        Material material = materialMapper.map(params);
        material.setId(uuidProvider.getUuid());
        Material savedMaterial = materialRepository.save(material);

        log.trace("Successfully created material with params - {}, result - {}", params, savedMaterial);
        return savedMaterial;
    }

    @Override
    public List<Material> findByCourseId(String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Finding materials by course id - {}", courseId);

        List<Material> materials = materialRepository.findByCourseId(courseId);

        log.trace("Successfully found materials by course id - {}, result - {}", courseId, materials);
        return materials;
    }

    @Override
    public void deleteById(String id) {
        Assert.hasText(id, "id should not be null");
        log.trace("Deleting material by id - {}", id);

        materialRepository.deleteById(id);

        log.trace("Successfully deleted material by id - {}", id);
    }

    @Override
    public void deleteByCourseId(String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        log.trace("Deleting materials by course id - {}", courseId);

        materialRepository.deleteByCourseId(courseId);

        log.trace("Successfully deleted materials by course id - {}", courseId);
    }

    @Override
    public Optional<Material> findById(String materialId) {
        Assert.hasText(materialId, "materialId should not be null");
        log.trace("Finding material by id - {}", materialId);

        final Optional<Material> material = materialRepository.findById(materialId);

        log.trace("Successfully found material by id - {}, result - {}", materialId, material);
        return material;
    }

    @Override
    public Optional<Material> findByIdAndCourseId(String materialId, String courseId) {
        Assert.hasText(courseId, "courseId should not be null");
        Assert.hasText(materialId, "materialId should not be null");
        log.trace("Finding material by id - {} adn course id - {}", materialId, courseId);

        final Optional<Material> material = materialRepository.findByIdAndCourseId(materialId, courseId);

        log.trace("Successfully found material by id - {} and course id - {}, result - {}", materialId, courseId, material);
        return material;
    }
}
