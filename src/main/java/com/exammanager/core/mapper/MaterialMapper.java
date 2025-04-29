package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.response.MaterialDto;
import com.exammanager.core.model.entity.Material;
import com.exammanager.core.model.params.CreateMaterialParams;

public interface MaterialMapper {

    Material map(CreateMaterialParams params);

    MaterialDto map(Material material);
}
