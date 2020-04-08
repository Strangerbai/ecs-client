package com.bee.sample.ecs.controller;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.controller.request.UserInfo;
import com.bee.sample.ecs.controller.response.Result;
import com.bee.sample.ecs.entity.ResultCode;
import com.bee.sample.ecs.service.UserInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dev-api/vue-element-admin/user")
public class UserInfoController {

    protected static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource(name = "userInfoServiceImpl")
    UserInfoService userInfoService;

    @RequestMapping(value = "/register")
    public Result registerUser(@RequestBody UserInfo userInfo){
        Result result = new Result();
        UserInfo userInfo1 = userInfoService.getUserByUsername(userInfo.getUsername());
        if(userInfo1!= null){
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
            return result;
        }
        boolean res =  userInfoService.createUser(userInfo);
        if(res){
            String token = userInfoService.getUserByUsername(userInfo.getUsername()).getToken();
            result.setCode(ResultCode.SUCCESS);
            result.setData(token);
            logger.info("token : {}", token);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/info")
    public Result getUserInfo(@RequestParam(name = "token") String token){
        UserInfo userInfo =  userInfoService.getUserByToken(token);
        Result result = new Result();
        if(userInfo != null){
            result.setCode(ResultCode.SUCCESS);
            result.setData(userInfo);
            logger.info("userInfo : {}", JSON.toJSONString(userInfo));
        } else{
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return result;



    }

    @RequestMapping(value = "/login")
    public Result login(@RequestBody UserInfo userInfo){
        UserInfo userInfo1 =  userInfoService.getUserByUsername(userInfo.getUsername());
        Result result = new Result();
        if(userInfo1!=null && userInfo1.getPassword().equals(userInfo.getPassword())){
            String token = userInfo1.getToken();
            result.setCode(ResultCode.SUCCESS);
            result.setData(token);
            logger.info("login : {}", JSON.toJSONString(userInfo1));
        }else{
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

}
