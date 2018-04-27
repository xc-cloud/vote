package com.workroom.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;

import com.workroom.domain.*;
import com.workroom.mapper.*;

@Service
public class ManagerService {
	@Autowired
	private matchsMapper matchs;
	@Autowired
	private playersMapper players;
	@Autowired
	private battlesMapper battles;
	@Autowired
	private battleflagtypeMapper battleflagtype;
	@Autowired
	private matchtypeMapper matchtype;
	@Autowired
	private votelistsMapper votelists;
	
	//投票处理
	
	public Integer PlayerTicket(votelists votelist) {
		if(votelists.selectPT(votelist)!=null ) return 0;
		battles ba = new battles();
		if(votelist.getTicketbol()==1){
			ba.setTickettruecount(1);
			ba.setTicketfalsecount(0);
		}else{
			ba.setTickettruecount(0);
			ba.setTicketfalsecount(1);
		}
		ba.setBattleid(votelist.getBattleid());
		ba.setMatchid(votelist.getMatchid());
		ba.setPlayerid(votelist.getPlayerid());
		return votelists.insert(votelist)>0 && battles.updateBattles(ba)>0?1:0;
	}
	
	//获取所有状态信息
	public List<matchtype> selectmatchtypeAll(){
		return matchtype.selectAll();
	}
	
	//获取所有评分标准信息
	public List<battleflagtype> selectbattleflagAll(){
		return battleflagtype.selectAll();
	}
	
	// 获取所有的matchs信息
	public List<matchs> selectmatchsAll() {
		return matchs.selectAll();
	}

	public matchs selectmatchsKey(Integer matchid) {
		return matchs.selectByPrimaryKey(matchid);
	}

	public int MatchaddInfo(matchs ms) {
		if (ms.getMatchid() != null) {
			return matchs.updateByPrimaryKey(ms) > 0 ? 0 : -1;
		} else {
			if (matchs.insert(ms) > 0)
				return matchs.getId();
			else
				return -1;
		}
	}

	public boolean Matchdelinfo(Integer matchid) {
		return matchs.deleteByPrimaryKey(matchid) > 0;
	}

	// 获取所有的player信息
	public List<players> selectplayerAll() {
		return players.selectAll();
	}
	
	public players selectplayerKey(Integer matchid) {
		return players.selectByPrimaryKey(matchid);
	}

	public Integer playeraddInfo(players p) {
		if (p.getPlayerid() != null) {
			return players.updateByPrimaryKey(p) > 0 ? 0 : -1;
		} else {
			if (players.insert(p) > 0)
				return players.getId();
			else
				return -1;
		}
	}

	public boolean playerdelinfo(Integer matchid) {
		return players.deleteByPrimaryKey(matchid) > 0;
	}

	// 获取所有的battles信息
	public List<battles> selectbattleAll() {
		return battles.selectAll();
	}

	public List<battles> selectAllOrderby() {
		return battles.selectAllOrderby();
	}
	
	public battles selectbattleKey(Integer battleid) {
		return battles.selectByPrimaryKey(battleid);
	}

	public Integer battleaddInfo(battles b) {
		if (b.getBattleid() != null) {
			try{
			return battles.updateByPrimaryKeySelective(b) > 0 ? 0 : -1;
			}catch(Exception e){
				return -1;
			}
		} else {
			if (battles.insert(b) > 0)
				return battles.getId();
			else
				return -1;
		}
	}
	public boolean battledelinfo(Integer battleid) {
		return battles.deleteByPrimaryKey(battleid) > 0;
	}
	
	//更新比赛状态
	public int updateMatch(Integer[] id){
		return battles.updateMatch(id);
	}
	//获取当前正在比赛的场次
	public List<battles> selectbattleTrue() {
		return battles.selectbattleTrue();
	}

	public battles selectBattleGetISTrue() {
		return battles.selectBattleGetISTrue();
	}
}
