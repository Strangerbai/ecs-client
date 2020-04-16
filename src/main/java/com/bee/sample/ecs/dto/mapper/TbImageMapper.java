package com.bee.sample.ecs.dto.mapper;

import com.bee.sample.ecs.dto.model.TbImage;
import com.bee.sample.ecs.dto.model.TbImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TbImageMapper {
    int insert(TbImage record);

    int insertSelective(TbImage record);

    List<TbImage> selectByExampleWithRowbounds(TbImageExample example, RowBounds rowBounds);

    List<TbImage> selectByExample(TbImageExample example);

    TbImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbImage record, @Param("example") TbImageExample example);

    int updateByExample(@Param("record") TbImage record, @Param("example") TbImageExample example);

    int updateByPrimaryKeySelective(TbImage record);

    int updateByPrimaryKey(TbImage record);
}