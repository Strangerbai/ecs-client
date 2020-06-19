package com.bee.sample.ecs.controller;

import com.alibaba.fastjson.JSON;
import com.bee.sample.ecs.controller.request.UserInfo;
import com.bee.sample.ecs.controller.response.Result;
import com.bee.sample.ecs.dto.model.TbBlog;
import com.bee.sample.ecs.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dev-api/vue-element-admin/blog")
public class BlogController {

    @Resource(name = "blogServiceImpl")
    BlogService blogService;


    @RequestMapping(value = "/create")
    public Result<Boolean> create(@RequestBody TbBlog blog){
        blog.setStatus(Byte.valueOf("1"));
        blog.setGmtCreate(new Date());
        blog.setGmtModified(new Date());
        log.info("blog:{}", JSON.toJSONString(blog));
        Boolean res = blogService.create(blog);
        return Result.success(res);
    }

    @RequestMapping(value = "/fetchAll")
    public Result<List<TbBlog>> fetchAll(){
        List<TbBlog> res = blogService.fetchAll();
        return Result.success(res);
    }


    @RequestMapping(value = "/fetchById")
    public Result<TbBlog> fetchById(@RequestParam("id") Long id){
        TbBlog blog = blogService.fetchById(id);
        return Result.success(blog);
    }


}
