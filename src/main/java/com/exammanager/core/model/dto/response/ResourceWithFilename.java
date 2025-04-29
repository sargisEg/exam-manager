package com.exammanager.core.model.dto.response;

import org.springframework.core.io.Resource;

public record ResourceWithFilename(Resource resource, String filename) {
}
