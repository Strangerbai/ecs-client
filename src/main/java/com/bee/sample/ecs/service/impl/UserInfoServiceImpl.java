package com.bee.sample.ecs.service.impl;

import com.bee.sample.ecs.controller.request.UserInfo;
import com.bee.sample.ecs.dto.mapper.TbUserInfoMapper;
import com.bee.sample.ecs.dto.model.TbUserInfo;
import com.bee.sample.ecs.dto.model.TbUserInfoExample;
import com.bee.sample.ecs.entity.Role;
import com.bee.sample.ecs.service.UserInfoService;
import com.bee.sample.ecs.utils.RandomUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    TbUserInfoMapper tbUserInfoMapper;

    @Override
    public boolean createUser(UserInfo userInfo) {
        TbUserInfo tbUserInfo = new TbUserInfo();
        BeanUtils.copyProperties(userInfo, tbUserInfo);
        tbUserInfo.setCreateTime(new Date());
        tbUserInfo.setUpdateTime(new Date());
        tbUserInfo.setToken(RandomUtil.getStringWithNumber(16));
        tbUserInfo.setRoles(Role.ADMIN.getValue());  // 默认是游客
        return tbUserInfoMapper.insertSelective(tbUserInfo) > 0;
    }

    @Override
    public UserInfo getUserByToken(String token) {
        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andTokenEqualTo(token);
        List<TbUserInfo> tbUserInfos =  tbUserInfoMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tbUserInfos)){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(tbUserInfos.get(0), userInfo);
            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo getUserByUsername(String userName) {
        TbUserInfoExample example = new TbUserInfoExample();
        example.createCriteria().andUsernameEqualTo(userName);
        List<TbUserInfo> tbUserInfos =  tbUserInfoMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tbUserInfos)){
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(tbUserInfos.get(0), userInfo);
            return userInfo;
        }
        return null;
    }
}
