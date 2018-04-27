package com.workroom.mapper;

import com.workroom.domain.votelists;

public interface votelistsMapper {
    int insert(votelists record);

    int insertSelective(votelists record);

	Integer selectPT(votelists record);
}