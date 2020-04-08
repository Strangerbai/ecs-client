package com.bee.sample.ecs.controller;

import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.service.IMemberInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserReditRestController {

    @Resource
    IMemberInfoService iMemberInfoService;

    @RequestMapping(value = "/userCredit/{status}")
    public List<TbMemberInfoDO> getCreditLevel(@PathVariable String status){
        List<TbMemberInfoDO> tbMemberInfoDOS =  iMemberInfoService.getMemberInfoByStatus(status);
        return tbMemberInfoDOS;
    }

}
