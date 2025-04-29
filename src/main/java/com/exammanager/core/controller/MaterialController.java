package com.exammanager.core.controller;

import com.exammanager.common.security.UserInfo;
import com.exammanager.common.security.UserInfoProvider;
import com.exammanager.core.facade.core.MaterialFacade;
import com.exammanager.core.model.dto.response.MaterialDto;
import com.exammanager.core.model.dto.response.ResourceWithFilename;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/core/v1/courses/{courseId}/materials")
@Secured("ROLE_TEACHER")
@SuppressWarnings("unused")
public class MaterialController {

    private final MaterialFacade materialFacade;

    @PostMapping
    ResponseEntity<Void> uploadMaterial(
            @PathVariable("courseId") String courseId,
            @RequestParam("file") MultipartFile file) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        materialFacade.uploadMaterial(userInfo, file, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{materialId}")
    ResponseEntity<Resource> downloadMaterial(@PathVariable("courseId") String courseId, @PathVariable("materialId") String materialId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        final ResourceWithFilename resourceWithFilename = materialFacade.downloadMaterial(userInfo, courseId, materialId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resourceWithFilename.filename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resourceWithFilename.resource());
    }

    @GetMapping()
    ResponseEntity<List<MaterialDto>> getMaterials(@PathVariable("courseId") String courseId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        final List<MaterialDto> materials = materialFacade.getMaterials(userInfo, courseId);
        return ResponseEntity.ok().body(materials);
    }

    @DeleteMapping("{materialId}")
    ResponseEntity<List<MaterialDto>> deleteMaterial(@PathVariable("courseId") String courseId, @PathVariable("materialId") String materialId) {
        final UserInfo userInfo = UserInfoProvider.getUserInfo();
        materialFacade.removeMaterialById(userInfo, courseId, materialId);
        return ResponseEntity.ok().build();
    }
}
