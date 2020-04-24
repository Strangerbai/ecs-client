package com.bee.sample.ecs.service;

import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import org.example.rpc.server.engine.BaseInterface;

import java.util.List;

public interface IMemberInfoService extends BaseInterface {

    String getMemberInfoByStatus(String status);

}
