package com.bee.sample.ecs.service;

import com.bee.sample.ecs.dto.model.TbBlog;

import java.util.List;

public interface BlogService {

    boolean create(TbBlog blog);

    boolean update(TbBlog blog);

    List<TbBlog> fetchAll();

    TbBlog fetchById(Long id);


    List<TbBlog> fetchByToken(String token);


}
