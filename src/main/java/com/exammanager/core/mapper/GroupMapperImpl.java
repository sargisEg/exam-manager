package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubgroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import com.exammanager.core.model.entity.Department;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateGroupParams;
import com.exammanager.core.model.params.CreateSubgroupParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupMapperImpl implements GroupMapper {

    private final DepartmentMapper departmentMapper;

    @Override
    public CreateGroupParams map(Department department, CreateGroupRequestDto dto) {
        return new CreateGroupParams(
                dto.getName(),
                dto.getStartYear(),
                dto.getEndYear(),
                department
        );
    }

    @Override
    public GroupDto map(Group group) {
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getStartYear(),
                group.getEndYear(),
                departmentMapper.map(group.getDepartment())
        );
    }

    @Override
    public Group map(CreateGroupParams params) {
        return new Group(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getName(),
                params.getStartYear(),
                params.getEndYear(),
                params.getDepartment()
        );
    }

    @Override
    public CreateSubgroupParams map(Group group, CreateSubgroupRequestDto dto) {
        return new CreateSubgroupParams(
                dto.getName(),
                group
        );
    }

    @Override
    public SubgroupDto map(Subgroup subgroup) {
        return new SubgroupDto(
                subgroup.getId(),
                subgroup.getName(),
                map(subgroup.getGroup())
        );
    }

    @Override
    public Subgroup map(CreateSubgroupParams params) {
        return new Subgroup(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                params.getName(),
                params.getGroup()
        );
    }
}
