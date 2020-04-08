package com.bee.sample.ecs.dto.mapper;

import com.bee.sample.ecs.dto.model.TbMemberInfoDO;
import com.bee.sample.ecs.dto.model.TbMemberInfoDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TbMemberInfoDOMapper {
    int insert(TbMemberInfoDO record);

    int insertSelective(TbMemberInfoDO record);

    List<TbMemberInfoDO> selectByExampleWithRowbounds(TbMemberInfoDOExample example, RowBounds rowBounds);

    List<TbMemberInfoDO> selectByExample(TbMemberInfoDOExample example);

    TbMemberInfoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbMemberInfoDO record, @Param("example") TbMemberInfoDOExample example);

    int updateByExample(@Param("record") TbMemberInfoDO record, @Param("example") TbMemberInfoDOExample example);
}
