package com.workroom.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workroom.SessionListener.MyUserList;
import com.workroom.domain.users;
import com.workroom.mapper.usersMapper;
@Service
public class userService {
	
	@Autowired
	private usersMapper us;
	//����Ա�û���¼
	public String selectNP(users u,HttpSession session,boolean flag){
		//�жϸ��û��Ƿ��Ѿ���¼������Ѿ���¼������ִ�к���Ĳ���������ʾ�û���ID�Ѿ���¼��
		List<HttpSession> sessions = MyUserList.getOnlineUsername();
		if(sessions!=null && sessions.size()>0){
		for(int i=0;i<sessions.size();i++){
			if(((users)(sessions.get(i).getAttribute("users"))).getUsername().equals(u.getUsername())){
				return "userisonline";
			}
		}
		}
		users un = us.selectNP(u);//ִ�е�¼����
		if(un!=null){
			//�ж��Ƿ�Ϊ����Ա��¼
			if(flag){
				//����Ա��¼
				if(un.getScale()!=9)
					return "scaleisnull";
				session.setAttribute("users", un);
				MyUserList.addUsername(session);
			}
		}
		return un!=null ? "true": "false";
	}
	public boolean insert(users u){
		u.setScale(0);//�����û���Ȩ��Ϊ0
		return us.insert(u)>0;//ִ���û�ע��
	}
	//��ͨ�û���¼
	public String selectUserLogin(users u, HttpSession session) {
		//�жϸ��û��Ƿ��Ѿ���¼������Ѿ���¼������ִ�к���Ĳ���������ʾ�û���ID�Ѿ���¼��
				List<HttpSession> sessions = MyUserList.getOnlineUsername();
				if(sessions!=null && sessions.size()>0){
				for(int i=0;i<sessions.size();i++){
					if(((users)(sessions.get(i).getAttribute("users"))).getUsername().equals(u.getUsername())){
						return "userisonline";
					}
				}
				}
				users un = us.selectNP(u);
				if(un!=null){
				//��ͨ�û���¼
				session.setAttribute("users", un);
				MyUserList.addUsername(session);
				}
		return un!=null ? "true": "false";
	}
}
