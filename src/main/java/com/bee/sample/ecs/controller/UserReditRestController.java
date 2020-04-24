package com.bee.sample.ecs.controller;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.service.IMemberInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserReditRestController {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserReditRestController.class);

    @Resource
    IMemberInfoService iMemberInfoService;

//    @RequestMapping(value = "/userCredit/{status}")
//    public List<TbMemberInfoDO> getCreditLevel(@PathVariable String status){
//        List<TbMemberInfoDO> tbMemberInfoDOS =  iMemberInfoService.getMemberInfoByStatus(status);
//        logger.info("tbMemberInfoDOS {}", JSON.toJSONString(tbMemberInfoDOS));
//        return tbMemberInfoDOS;
//    }

}
