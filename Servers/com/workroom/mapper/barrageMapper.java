package com.workroom.mapper;

import com.workroom.domain.barrage;

public interface barrageMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(barrage record);

    int insertSelective(barrage record);

    barrage selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(barrage record);

    int updateByPrimaryKey(barrage record);
}