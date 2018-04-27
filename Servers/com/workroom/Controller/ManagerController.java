package com.workroom.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workroom.SessionListener.MyUserList;
import com.workroom.domain.*;
import com.workroom.service.ManagerService;

@Controller
public class ManagerController {
	@Autowired
	private ManagerService manager;
	//是否为管理员登录状态
	public boolean ManagerisLogin(HttpSession session){
		try{
			if(((users)(session.getAttribute("users"))).getUsername()!=null && ((users)(session.getAttribute("users"))).getScale()==9)
				return true;
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	//是否为普通用户登录状态
	public users GetUser(HttpSession session){
		try{
			if(((users)(session.getAttribute("users")))!=null)
				return ((users)(session.getAttribute("users")));
			else{
				return null;
			}
		}catch(Exception e){
			return null;
		}
	}
	//开启或关闭一场比赛
	@RequestMapping("/UpdateMatch")
	@ResponseBody
	public boolean StartMatch(@RequestBody Integer[] id,HttpSession session) {
		if(!ManagerisLogin(session)||id==null || id.length<2)return false; 
		System.err.println("前台获取ID为"+id[0]);
		System.err.println("前台获取match为"+id[1]);
		return manager.updateMatch(id)>0;
	}
	//获取比赛状态
	@RequestMapping("/GetMatchType")
	@ResponseBody
	public List<matchtype> GetMatchType(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectmatchtypeAll();
	}
	
	//获取比赛类型
	@RequestMapping("/GetBattleflagType")
	@ResponseBody
	public List<battleflagtype> GetBattleflagType(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectbattleflagAll();
	}
	
	// 比赛管理页面获取
	@RequestMapping("/MatchManager")
	@ResponseBody
	public List<matchs> getMatchManager(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectmatchsAll();
	}

	// 比赛指定信息获取
	@RequestMapping("/MatchGetinfo")
	@ResponseBody
	public matchs MatchGetinfo(@RequestBody matchs m, HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		System.err.println("前台传参数据" + m.getMatchid());
		return manager.selectmatchsKey(m.getMatchid());
	}

	// 比赛信息添加或修改
	@RequestMapping("/MatchaddInfo")
	@ResponseBody
	public int MatchaddInfo(@RequestBody @Valid  matchs m,Errors errors,HttpSession session) {
		if(!ManagerisLogin(session))return -1; //判断管理员是否登录
		if(errors.getErrorCount()>0)return -1;//判断是否存在错误信息
		return manager.MatchaddInfo(m);//执行操作
	}

	// 比赛信息删除
	@RequestMapping("/Matchdelinfo")
	@ResponseBody
	public boolean Matchdelinfo(@RequestBody matchs m, HttpSession session) {
		if(!ManagerisLogin(session))return false; 
		System.err.println("前台传参数据" + m.getMatchid());
		return manager.Matchdelinfo(m.getMatchid());
	}

	// 选手管理页面获取
	@RequestMapping("/PlayerManager")
	@ResponseBody
	public List<players> getPlayerManager(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectplayerAll();
	}
	//排序输出所有场次
	@RequestMapping("/selectAllOrderby")
	@ResponseBody
	public List<battles> selectAllOrderby(HttpSession session) {
		if(GetUser(session)==null) return null;
		return manager.selectAllOrderby();
	}
	
	// 选手指定信息获取
	@RequestMapping("/PlayerGetinfo")
	@ResponseBody
	public players PlayerGetinfo(@RequestBody players p, HttpSession session) {
		if(GetUser(session)==null) return null;
		System.err.println("前台传参数据" + p.getPlayerid());
		return manager.selectplayerKey(p.getPlayerid());
	}

	// 选手信息添加或修改
	@RequestMapping("/PlayeraddInfo")
	@ResponseBody
	public Integer PlayeraddInfo(@Valid @RequestBody players p,Errors errors, HttpSession session) {
		if(!ManagerisLogin(session))return -1; //判断管理员是否登录
		if(errors.getErrorCount()>0)return -1;//判断是否存在错误信息
		return manager.playeraddInfo(p);//执行操作
	}

	// 选手信息删除
	@RequestMapping("/Playerdelinfo")
	@ResponseBody
	public boolean Playerdelinfo(@RequestBody players p, HttpSession session) {
		if(!ManagerisLogin(session))return false; 
		System.err.println("前台传参数据" + p.getPlayerid());
		return manager.playerdelinfo(p.getPlayerid());
	}
	
	// 场次管理页面获取
	@RequestMapping("/BattleManager")
	@ResponseBody
	public List<battles> getBattleManager(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectbattleAll();
	}

	// 场次指定信息获取
	@RequestMapping("/BattleGetinfo")
	@ResponseBody
	public battles BattleGetinfo(@RequestBody battles b, HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		System.err.println("前台传参数据" +b.getBattleid());
		return manager.selectbattleKey(b.getBattleid());
	}
	//获取正在比赛的场次
	@RequestMapping("/BattleGetTrue")
	@ResponseBody
	public List<battles> BattleGetTrue() {
		return manager.selectbattleTrue();
	}
	
	// 获取当前比赛胜利方的信息
	@RequestMapping("/BattleGetISTrue")
	@ResponseBody
	public battles BattleGetISTrue() {
		return manager.selectBattleGetISTrue();
	}
	
	// 场次信息添加或修改
	@RequestMapping("/BattleaddInfo")
	@ResponseBody
	public Integer BattleaddInfo(@Valid @RequestBody battles b,Errors errors,HttpSession session) {
		if(!ManagerisLogin(session))return -1; //判断管理员是否已登录
		if(errors.getErrorCount()>0)return -1;//判断是否存在错误信息
		return manager.battleaddInfo(b);//执行操作
	}
	
	// 场次信息删除
	@RequestMapping("/Battledelinfo")
	@ResponseBody
	public boolean Battledelinfo(@RequestBody battles b, HttpSession session) {
		if(!ManagerisLogin(session))return false; 
		System.err.println("前台传参数据" + b.getBattleid());
		return manager.battledelinfo(b.getBattleid());
	}

	//场次选手票数增加
	@RequestMapping("/PlayerTicket")//投票
	@ResponseBody
	public Integer PlayerTicketTrue(@Valid @RequestBody votelists vote,Errors errors,HttpSession session) {
		if(errors.getErrorCount()>0)return -1;//判断是否存在错误信息
		users u= GetUser(session);
		if(u==null)return -1;//判断用户是否已登录
		vote.setUserid(u.getUserid());//从session中读取用户ID存入vote
		return manager.PlayerTicket(vote);//执行操作
	}
}
