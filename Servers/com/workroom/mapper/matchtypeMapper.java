package com.workroom.mapper;

import java.util.List;

import com.workroom.domain.matchtype;

public interface matchtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(matchtype record);

    int insertSelective(matchtype record);

    matchtype selectByPrimaryKey(Integer id);
    
    List<matchtype> selectAll();
    
    int updateByPrimaryKeySelective(matchtype record);

    int updateByPrimaryKey(matchtype record);
}