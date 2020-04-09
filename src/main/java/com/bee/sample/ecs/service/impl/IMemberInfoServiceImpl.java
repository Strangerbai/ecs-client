package com.bee.sample.ecs.service.impl;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.controller.UserReditRestController;
import com.bee.sample.ecs.dto.mapper.TbMemberInfoDOMapper;
import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.dto.model.TbMemberInfoDOExample;
import com.bee.sample.ecs.service.IMemberInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IMemberInfoServiceImpl implements IMemberInfoService {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(IMemberInfoServiceImpl.class);

    @Resource
    TbMemberInfoDOMapper tbMemberInfoDOMapper;

    @Override
    public List<TbMemberInfoDO> getMemberInfoByStatus(String status) {
        TbMemberInfoDOExample example = new TbMemberInfoDOExample();
        example.createCriteria().andStatusEqualTo(Byte.valueOf(status));
        List<TbMemberInfoDO> result = tbMemberInfoDOMapper.selectByExample(example);
        logger.info("getMemberInfoByStatus result : {}", JSON.toJSONString(result));
        return result;
    }
}
