package com.bee.sample.ecs.service;

import com.bee.sample.ecs.dto.model.TbMemberInfoDO;

import java.util.List;

public interface IMemberInfoService {

    List<TbMemberInfoDO> getMemberInfoByStatus(String status);

}
