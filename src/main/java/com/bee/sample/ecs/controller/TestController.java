package com.bee.sample.ecs.controller;


import com.bee.sample.ecs.controller.request.UserCreditReq;
import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.service.IMemberInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

//@RestController
//@RequestMapping("/")
public class TestController {

    @Resource
    IMemberInfoService iMemberInfoService;

    @RequestMapping(value = "userCredit")
    public List<TbMemberInfoDO> getCreditLevel(@RequestBody UserCreditReq userCreditReq){
        List<TbMemberInfoDO> tbMemberInfoDOS =  iMemberInfoService.getMemberInfoByStatus(userCreditReq.getStatus());
        return tbMemberInfoDOS;
    }

}
