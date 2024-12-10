package com.exammanager.common.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidProviderImpl implements UuidProvider {
    @Override
    public String getUuid() {
        return UUID.randomUUID().toString();
    }
}
