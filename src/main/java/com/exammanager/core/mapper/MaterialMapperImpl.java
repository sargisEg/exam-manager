package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.response.MaterialDto;
import com.exammanager.core.model.entity.Material;
import com.exammanager.core.model.params.CreateMaterialParams;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapperImpl implements MaterialMapper {

    @Override
    public Material map(CreateMaterialParams params) {
        return new Material(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getName(),
                params.getCourse(),
                params.getLocation(),
                params.getExtension(),
                params.getSize()
        );
    }

    @Override
    public MaterialDto map(Material material) {
        return new MaterialDto(
                material.getId(),
                material.getName(),
                material.getCourse().getId(),
                material.getSize()
        );
    }
}
