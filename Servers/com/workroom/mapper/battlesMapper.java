package com.workroom.mapper;

import java.util.List;

import com.workroom.domain.battles;
import com.workroom.domain.players;

public interface battlesMapper {
    int deleteByPrimaryKey(Integer battleid);

    int insert(battles record);

    int insertSelective(battles record);

    battles selectByPrimaryKey(Integer battleid);

    int updateByPrimaryKeySelective(battles record);

    int updateByPrimaryKeyWithBLOBs(battles record);

    int updateByPrimaryKey(battles record);
    
    int updateMatch(Integer[] id);
    
	Integer getId();

	List<battles> selectAll();

	List<battles> selectbattleTrue();
	

	List<battles> selectAllOrderby();

	battles selectBattleGetISTrue();

	Integer updateBattles(battles ba);

}