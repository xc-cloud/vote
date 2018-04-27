package com.workroom.mapper;

import java.util.List;

import com.workroom.domain.players;

public interface playersMapper {
    int deleteByPrimaryKey(Integer playerid);

    int insert(players record);

    int insertSelective(players record);

    players selectByPrimaryKey(Integer playerid);

    int updateByPrimaryKeySelective(players record);

    int updateByPrimaryKey(players record);

	List<players> selectAll();
	int getId();
}