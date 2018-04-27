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
	//管理员用户登录
	public String selectNP(users u,HttpSession session,boolean flag){
		//判断该用户是否已经登录，如果已经登录，将不执行后面的操作，并提示用户该ID已经登录！
		List<HttpSession> sessions = MyUserList.getOnlineUsername();
		if(sessions!=null && sessions.size()>0){
		for(int i=0;i<sessions.size();i++){
			if(((users)(sessions.get(i).getAttribute("users"))).getUsername().equals(u.getUsername())){
				return "userisonline";
			}
		}
		}
		users un = us.selectNP(u);//执行登录操作
		if(un!=null){
			//判断是否为管理员登录
			if(flag){
				//管理员登录
				if(un.getScale()!=9)
					return "scaleisnull";
				session.setAttribute("users", un);
				MyUserList.addUsername(session);
			}
		}
		return un!=null ? "true": "false";
	}
	public boolean insert(users u){
		u.setScale(0);//设置用户的权限为0
		return us.insert(u)>0;//执行用户注册
	}
	//普通用户登录
	public String selectUserLogin(users u, HttpSession session) {
		//判断该用户是否已经登录，如果已经登录，将不执行后面的操作，并提示用户该ID已经登录！
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
				//普通用户登录
				session.setAttribute("users", un);
				MyUserList.addUsername(session);
				}
		return un!=null ? "true": "false";
	}
}
