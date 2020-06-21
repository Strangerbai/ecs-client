package com.bee.sample.ecs.controller;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.controller.request.UserInfo;
import com.bee.sample.ecs.controller.response.BlogVo;
import com.bee.sample.ecs.controller.response.Result;
import com.bee.sample.ecs.dto.model.TbBlog;
import com.bee.sample.ecs.service.BlogService;
import com.bee.sample.ecs.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/dev-api/vue-element-admin/blog")
public class BlogController {

    @Resource(name = "blogServiceImpl")
    BlogService blogService;

    @Resource(name = "userInfoServiceImpl")
    UserInfoService userInfoService;

    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping(value = "/create")
    public Result<Boolean> create(@RequestBody TbBlog blog){
        log.info("blog create:{}", JSON.toJSONString(blog));
        Boolean res;
        if(null == blog.getId()){
            blog.setStatus(Byte.valueOf("1"));
            blog.setGmtCreate(new Date());
            blog.setGmtModified(new Date());
            res = blogService.create(blog);
        } else {
            blog.setGmtModified(new Date());
            blog.setGmtCreate(blogService.fetchById(blog.getId()).getGmtCreate());
            blog.setStatus(Byte.valueOf("1"));
            log.info("blog update:{}", JSON.toJSONString(blog));
            res = blogService.update(blog);
        }
        return Result.success(res);
    }

    @RequestMapping(value = "/update")
    public Result<Boolean> update(@RequestBody TbBlog blog){
        blog.setGmtModified(new Date());
        log.info("blog update:{}", JSON.toJSONString(blog));
        Boolean res = blogService.update(blog);
        return Result.success(res);
    }

    @RequestMapping(value = "/fetchAll")
    public Result<List<BlogVo>> fetchAll(){
        List<BlogVo> blogVos = new ArrayList<>();
        List<TbBlog> res = blogService.fetchAll();
        blogVos = res.stream().map(e->{
            BlogVo blogVo = new BlogVo();
            blogVo.setContent(e.getContent());
            blogVo.setDescription(e.getDescription());
            blogVo.setId(e.getId());
            blogVo.setTitle(e.getTitle());
            blogVo.setName(userInfoService.getUserByToken(e.getToken()).getUsername());
            blogVo.setStatus(e.getStatus());
            blogVo.setGmtCreate(sdf1.format(e.getGmtCreate()));
            return blogVo;
        }).collect(toList());
        return Result.success(blogVos);
    }


    @RequestMapping(value = "/fetchById")
    public Result<TbBlog> fetchById(@RequestParam("id") Long id){
        TbBlog blog = blogService.fetchById(id);
        return Result.success(blog);
    }


    @RequestMapping(value = "/fetchByToken")
    public Result<List<TbBlog>> fetchByToken(@RequestParam("token") String token){
        List<TbBlog> blog = blogService.fetchByToken(token);
        return Result.success(blog);
    }




}
