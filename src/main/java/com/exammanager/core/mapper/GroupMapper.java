package com.exammanager.core.mapper;

import com.exammanager.core.model.dto.request.CreateGroupRequestDto;
import com.exammanager.core.model.dto.request.CreateSubgroupRequestDto;
import com.exammanager.core.model.dto.response.GroupDto;
import com.exammanager.core.model.dto.response.SubgroupDto;
import com.exammanager.core.model.entity.Group;
import com.exammanager.core.model.entity.Subgroup;
import com.exammanager.core.model.params.CreateGroupParams;
import com.exammanager.core.model.params.CreateSubgroupParams;

public interface GroupMapper {

    CreateGroupParams map(CreateGroupRequestDto dto);

    GroupDto map(Group group);

    Group map(CreateGroupParams params);

    CreateSubgroupParams map(Group group, CreateSubgroupRequestDto dto);

    SubgroupDto map(Subgroup subgroup);

    Subgroup map(CreateSubgroupParams params);
}
