package com.bee.sample.ecs.dto.mapper;

import com.bee.sample.ecs.dto.model.TbUserInfo;
import com.bee.sample.ecs.dto.model.TbUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TbUserInfoMapper {
    int insert(TbUserInfo record);

    int insertSelective(TbUserInfo record);

    List<TbUserInfo> selectByExampleWithRowbounds(TbUserInfoExample example, RowBounds rowBounds);

    List<TbUserInfo> selectByExample(TbUserInfoExample example);

    TbUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserInfo record, @Param("example") TbUserInfoExample example);

    int updateByExample(@Param("record") TbUserInfo record, @Param("example") TbUserInfoExample example);

    int updateByPrimaryKeySelective(TbUserInfo record);

    int updateByPrimaryKey(TbUserInfo record);
}
