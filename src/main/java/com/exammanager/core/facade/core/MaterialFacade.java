package com.exammanager.core.facade.core;

import com.exammanager.common.security.UserInfo;
import com.exammanager.core.model.dto.response.MaterialDto;
import com.exammanager.core.model.dto.response.ResourceWithFilename;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaterialFacade {

    MaterialDto uploadMaterial(UserInfo userInfo, MultipartFile file, String courseId);

    ResourceWithFilename downloadMaterial(UserInfo userInfo, String courseId, String materialId);

    List<MaterialDto> getMaterials(UserInfo userInfo, String courseId);

    void removeMaterialById(UserInfo userInfo, String courseId, String materialId);

    void removeMaterialByCourseId(UserInfo userInfo, String courseId);

    List<MaterialDto> getMaterials(UserInfo userInfo);
}
