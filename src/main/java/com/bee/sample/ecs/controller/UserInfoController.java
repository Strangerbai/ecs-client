package com.bee.sample.ecs.controller;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.controller.request.UserInfo;
import com.bee.sample.ecs.controller.response.Result;
import com.bee.sample.ecs.entity.EcsConstant;
import com.bee.sample.ecs.entity.ResultCode;
import com.bee.sample.ecs.service.UserInfoService;
import com.bee.sample.ecs.service.impl.ImageServiceImpl;
import com.bee.sample.ecs.utils.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/dev-api/vue-element-admin/user")
public class UserInfoController {

    @Resource(name = "userInfoServiceImpl")
    UserInfoService userInfoService;

    @Resource
    ImageServiceImpl imageService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/register")
    public Result<String> registerUser(@RequestBody UserInfo userInfo){
        UserInfo userInfo1 = userInfoService.getUserByUsername(userInfo.getUsername());
        if(userInfo1!= null){
            return Result.fail(ResultCode.FAIL);
        }
        boolean res =  userInfoService.createUser(userInfo);
        if(res){
            String token = userInfoService.getUserByUsername(userInfo.getUsername()).getToken();
            log.info("token : {}", token);
            return Result.success(token);
        }
        return Result.fail(ResultCode.FAIL);
    }

    @RequestMapping(value = "/info")
    public Result<UserInfo> getUserInfo(@RequestParam(name = "token") String token){
        UserInfo userInfo =  userInfoService.getUserByToken(token);
        if(userInfo != null){
            log.info("userInfo : {}", JSON.toJSONString(userInfo));
            return Result.success(userInfo);
        } else{
            return Result.fail(ResultCode.FAIL.getCode(),"用户已注册");
        }
    }

    @RequestMapping(value = "/login")
    public Result<String> login(@RequestBody UserInfo userInfo){
        UserInfo userInfo1 =  userInfoService.getUserByUsername(userInfo.getUsername());
        if(userInfo1!=null && userInfo1.getPassword().equals(userInfo.getPassword())){
            String token = userInfo1.getToken();
            log.info("login : {}", JSON.toJSONString(userInfo1));
            return Result.success(token);
        }else{
            return Result.fail(ResultCode.FAIL.getCode(),"登录失败");
        }
    }

    @RequestMapping(value = "/logout")
    public Result<Boolean> logout(){
        return Result.success(Boolean.TRUE);
    }


    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public Result<String> uploadImg(@RequestPart(name = "file") MultipartFile file) throws IOException {
        log.info("fileName : {}, originFileName : {}", file.getName(), file.getOriginalFilename());
        String url = imageService.uploadImage(file);
        return Result.success(url);
    }


    @RequestMapping(value = "/getPictureName")
    public Result<List<String>> getPictureName(){
        List<String> pictures = imageService.getUserPictureName();
        log.info("picture:{}", pictures);
        return Result.success(pictures);
    }



}
