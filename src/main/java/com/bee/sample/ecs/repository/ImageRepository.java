package com.bee.sample.ecs.repository;

import com.bee.sample.ecs.dto.mapper.TbImageMapper;
import com.bee.sample.ecs.dto.model.TbImage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ImageRepository {

    @Resource
    TbImageMapper tbImageMapper;

    public boolean saveImageName(String fileName, String fileType, String token){
        TbImage tbImage = new TbImage();
        tbImage.setImageName(fileName);
        tbImage.setImageType(fileType);
        tbImage.setToken(token);
        return tbImageMapper.insertSelective(tbImage) > 0;
    }
}
