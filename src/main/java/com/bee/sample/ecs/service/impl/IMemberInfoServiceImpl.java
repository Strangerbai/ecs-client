package com.bee.sample.ecs.service.impl;

import com.bee.sample.ecs.dto.mapper.TbMemberInfoDOMapper;
import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.dto.model.TbMemberInfoDOExample;
import com.bee.sample.ecs.service.IMemberInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IMemberInfoServiceImpl implements IMemberInfoService {

    @Resource
    TbMemberInfoDOMapper tbMemberInfoDOMapper;

    @Override
    public List<TbMemberInfoDO> getMemberInfoByStatus(String status) {
        TbMemberInfoDOExample example = new TbMemberInfoDOExample();
        example.createCriteria().andStatusEqualTo(Byte.valueOf(status));
        List<TbMemberInfoDO> result = tbMemberInfoDOMapper.selectByExample(example);
        return result;
    }
}
