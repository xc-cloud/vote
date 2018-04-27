package com.workroom.mapper;

import java.util.List;

import com.workroom.domain.battleflagtype;

public interface battleflagtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(battleflagtype record);

    int insertSelective(battleflagtype record);

    battleflagtype selectByPrimaryKey(Integer id);
    
    List<battleflagtype> selectAll();

    int updateByPrimaryKeySelective(battleflagtype record);

    int updateByPrimaryKey(battleflagtype record);
}