package com.workroom.mapper;

import java.util.List;

import com.workroom.domain.matchs;

public interface matchsMapper {
    int deleteByPrimaryKey(Integer matchid);

    int insert(matchs record);

    int insertSelective(matchs record);

    matchs selectByPrimaryKey(Integer matchid);

    int updateByPrimaryKeySelective(matchs record);

    int updateByPrimaryKey(matchs record);

	List<matchs> selectAll();

	int getId();
}