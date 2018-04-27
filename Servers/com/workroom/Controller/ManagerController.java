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
	//�Ƿ�Ϊ����Ա��¼״̬
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
	//�Ƿ�Ϊ��ͨ�û���¼״̬
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
	//������ر�һ������
	@RequestMapping("/UpdateMatch")
	@ResponseBody
	public boolean StartMatch(@RequestBody Integer[] id,HttpSession session) {
		if(!ManagerisLogin(session)||id==null || id.length<2)return false; 
		System.err.println("ǰ̨��ȡIDΪ"+id[0]);
		System.err.println("ǰ̨��ȡmatchΪ"+id[1]);
		return manager.updateMatch(id)>0;
	}
	//��ȡ����״̬
	@RequestMapping("/GetMatchType")
	@ResponseBody
	public List<matchtype> GetMatchType(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectmatchtypeAll();
	}
	
	//��ȡ��������
	@RequestMapping("/GetBattleflagType")
	@ResponseBody
	public List<battleflagtype> GetBattleflagType(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectbattleflagAll();
	}
	
	// ��������ҳ���ȡ
	@RequestMapping("/MatchManager")
	@ResponseBody
	public List<matchs> getMatchManager(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectmatchsAll();
	}

	// ����ָ����Ϣ��ȡ
	@RequestMapping("/MatchGetinfo")
	@ResponseBody
	public matchs MatchGetinfo(@RequestBody matchs m, HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		System.err.println("ǰ̨��������" + m.getMatchid());
		return manager.selectmatchsKey(m.getMatchid());
	}

	// ������Ϣ��ӻ��޸�
	@RequestMapping("/MatchaddInfo")
	@ResponseBody
	public int MatchaddInfo(@RequestBody @Valid  matchs m,Errors errors,HttpSession session) {
		if(!ManagerisLogin(session))return -1; //�жϹ���Ա�Ƿ��¼
		if(errors.getErrorCount()>0)return -1;//�ж��Ƿ���ڴ�����Ϣ
		return manager.MatchaddInfo(m);//ִ�в���
	}

	// ������Ϣɾ��
	@RequestMapping("/Matchdelinfo")
	@ResponseBody
	public boolean Matchdelinfo(@RequestBody matchs m, HttpSession session) {
		if(!ManagerisLogin(session))return false; 
		System.err.println("ǰ̨��������" + m.getMatchid());
		return manager.Matchdelinfo(m.getMatchid());
	}

	// ѡ�ֹ���ҳ���ȡ
	@RequestMapping("/PlayerManager")
	@ResponseBody
	public List<players> getPlayerManager(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectplayerAll();
	}
	//����������г���
	@RequestMapping("/selectAllOrderby")
	@ResponseBody
	public List<battles> selectAllOrderby(HttpSession session) {
		if(GetUser(session)==null) return null;
		return manager.selectAllOrderby();
	}
	
	// ѡ��ָ����Ϣ��ȡ
	@RequestMapping("/PlayerGetinfo")
	@ResponseBody
	public players PlayerGetinfo(@RequestBody players p, HttpSession session) {
		if(GetUser(session)==null) return null;
		System.err.println("ǰ̨��������" + p.getPlayerid());
		return manager.selectplayerKey(p.getPlayerid());
	}

	// ѡ����Ϣ��ӻ��޸�
	@RequestMapping("/PlayeraddInfo")
	@ResponseBody
	public Integer PlayeraddInfo(@Valid @RequestBody players p,Errors errors, HttpSession session) {
		if(!ManagerisLogin(session))return -1; //�жϹ���Ա�Ƿ��¼
		if(errors.getErrorCount()>0)return -1;//�ж��Ƿ���ڴ�����Ϣ
		return manager.playeraddInfo(p);//ִ�в���
	}

	// ѡ����Ϣɾ��
	@RequestMapping("/Playerdelinfo")
	@ResponseBody
	public boolean Playerdelinfo(@RequestBody players p, HttpSession session) {
		if(!ManagerisLogin(session))return false; 
		System.err.println("ǰ̨��������" + p.getPlayerid());
		return manager.playerdelinfo(p.getPlayerid());
	}
	
	// ���ι���ҳ���ȡ
	@RequestMapping("/BattleManager")
	@ResponseBody
	public List<battles> getBattleManager(HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		return manager.selectbattleAll();
	}

	// ����ָ����Ϣ��ȡ
	@RequestMapping("/BattleGetinfo")
	@ResponseBody
	public battles BattleGetinfo(@RequestBody battles b, HttpSession session) {
		if(!ManagerisLogin(session))return null; 
		System.err.println("ǰ̨��������" +b.getBattleid());
		return manager.selectbattleKey(b.getBattleid());
	}
	//��ȡ���ڱ����ĳ���
	@RequestMapping("/BattleGetTrue")
	@ResponseBody
	public List<battles> BattleGetTrue() {
		return manager.selectbattleTrue();
	}
	
	// ��ȡ��ǰ����ʤ��������Ϣ
	@RequestMapping("/BattleGetISTrue")
	@ResponseBody
	public battles BattleGetISTrue() {
		return manager.selectBattleGetISTrue();
	}
	
	// ������Ϣ��ӻ��޸�
	@RequestMapping("/BattleaddInfo")
	@ResponseBody
	public Integer BattleaddInfo(@Valid @RequestBody battles b,Errors errors,HttpSession session) {
		if(!ManagerisLogin(session))return -1; //�жϹ���Ա�Ƿ��ѵ�¼
		if(errors.getErrorCount()>0)return -1;//�ж��Ƿ���ڴ�����Ϣ
		return manager.battleaddInfo(b);//ִ�в���
	}
	
	// ������Ϣɾ��
	@RequestMapping("/Battledelinfo")
	@ResponseBody
	public boolean Battledelinfo(@RequestBody battles b, HttpSession session) {
		if(!ManagerisLogin(session))return false; 
		System.err.println("ǰ̨��������" + b.getBattleid());
		return manager.battledelinfo(b.getBattleid());
	}

	//����ѡ��Ʊ������
	@RequestMapping("/PlayerTicket")//ͶƱ
	@ResponseBody
	public Integer PlayerTicketTrue(@Valid @RequestBody votelists vote,Errors errors,HttpSession session) {
		if(errors.getErrorCount()>0)return -1;//�ж��Ƿ���ڴ�����Ϣ
		users u= GetUser(session);
		if(u==null)return -1;//�ж��û��Ƿ��ѵ�¼
		vote.setUserid(u.getUserid());//��session�ж�ȡ�û�ID����vote
		return manager.PlayerTicket(vote);//ִ�в���
	}
}
