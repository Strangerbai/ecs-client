package com.bee.sample.ecs.repository;

import com.bee.sample.ecs.dto.mapper.TbImageMapper;
import com.bee.sample.ecs.dto.model.TbImage;
import com.bee.sample.ecs.dto.model.TbImageExample;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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

    public List<TbImage> getImageInfo(String token){
        TbImageExample example = new TbImageExample();
        example.createCriteria().andTokenEqualTo(token);
        List<TbImage> images =  tbImageMapper.selectByExample(example);
        return images;
    }
}
