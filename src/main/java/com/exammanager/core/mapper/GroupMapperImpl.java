package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubGroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateGroupParams;
import com.exammanager.core.model.params.CreateSubgroupParams;
import org.springframework.stereotype.Component;

@Component
public class GroupMapperImpl implements GroupMapper {
    @Override
    public CreateGroupParams map(Department department, CreateGroupRequestDto dto) {
        return null;
    }

    @Override
    public GroupDto map(Group group) {
        return null;
    }

    @Override
    public CreateSubgroupParams map(Group group, CreateSubGroupRequestDto dto) {
        return null;
    }

    @Override
    public SubgroupDto map(Subgroup subgroup) {
        return null;
    }
}
