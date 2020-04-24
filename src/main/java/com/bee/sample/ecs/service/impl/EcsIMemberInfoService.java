package com.bee.sample.ecs.service.impl;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.controller.UserReditRestController;
import com.bee.sample.ecs.dto.mapper.TbMemberInfoDOMapper;
import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.dto.model.TbMemberInfoDOExample;
import com.bee.sample.ecs.service.IMemberInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class EcsIMemberInfoService implements IMemberInfoService {

    @Resource
    TbMemberInfoDOMapper tbMemberInfoDOMapper;

    @Override
    public String getMemberInfoByStatus(String status) {
        TbMemberInfoDOExample example = new TbMemberInfoDOExample();
        example.createCriteria().andStatusEqualTo(Byte.valueOf(status));
        List<TbMemberInfoDO> result = tbMemberInfoDOMapper.selectByExample(example);
        log.info("getMemberInfoByStatus result : {}", JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }
}
