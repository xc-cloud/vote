package com.workroom.mapper;

import java.util.List;

import com.workroom.domain.users;

public interface usersMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(users record);

    int insertSelective(users record);

    users selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(users record);

    int updateByPrimaryKey(users record);

	users selectNP(users u);
}