package com.bee.sample.ecs.service.impl;

import com.bee.sample.ecs.dto.mapper.TbBlogMapper;
import com.bee.sample.ecs.dto.model.TbBlog;
import com.bee.sample.ecs.dto.model.TbBlogExample;
import com.bee.sample.ecs.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    TbBlogMapper tbBlogMapper;

    @Override
    public boolean create(TbBlog blog) {
        return tbBlogMapper.insertSelective(blog)>0;
    }

    @Override
    public boolean update(TbBlog blog) {
        TbBlogExample example = new TbBlogExample();
        example.createCriteria().andIdEqualTo(blog.getId());
        return tbBlogMapper.updateByExampleWithBLOBs(blog, example)>0;
    }

    @Override
    public List<TbBlog> fetchAll() {
        TbBlogExample example = new TbBlogExample();
        example.createCriteria().andIdGreaterThan(0L);
        return tbBlogMapper.selectByExample(example);
    }

    @Override
    public TbBlog fetchById(Long id) {
        return tbBlogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbBlog> fetchByToken(String token) {
        TbBlogExample example = new TbBlogExample();
        example.createCriteria().andTokenEqualTo(token);
        return tbBlogMapper.selectByExampleWithBLOBs(example);
    }
}
