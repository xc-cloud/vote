package com.workroom.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.workroom.SessionListener.MyUserList;
import com.workroom.domain.users;
import com.workroom.service.userService;

@Controller
public class UsersController {

	@Autowired
	private userService us;

	// 管理员登录。如果是普通用户尝试登录，将拒绝登录
	@RequestMapping("/managerLogin")
	@ResponseBody
	public String[] managerLogin(@RequestBody users u, HttpSession session) {
		String[] list = new String[1];
		list[0] = us.selectNP(u, session, true);
		return list;
	}

	// 普通用户登录
	@RequestMapping("/UserLogin")
	@ResponseBody
	public String[] UserLogin(@RequestBody users u, HttpSession session) {
		String[] list = new String[1];
		System.out.println("前台传入用户名" + u.getUsername());
		System.out.println(u.getPassword());
		list[0] = us.selectUserLogin(u, session);
		return list;
	}

	// 判断是否登录，如果登录，将返回true，否则返回false并跳转到登录页面
	@RequestMapping("/isLogin")
	@ResponseBody
	public boolean managerisLogin(HttpSession session) {
		return session.getAttribute("users") != null ? true : false;
	}

	// 获取管理员信息
	@RequestMapping("/getmanagerdata")
	@ResponseBody
	public users getmanagerdata(HttpSession session) {
		return session.getAttribute("users") != null ? (users) session.getAttribute("users") : null;
	}

	// 获取在线人数
	@RequestMapping("/getUserSize")
	@ResponseBody
	public int getUserSize(HttpSession session) {
		System.out.println("当前在线人数" + MyUserList.getOnlineUsername().size());
		return MyUserList.getOnlineUsername().size();
	}

	// 普通用户注册
	@RequestMapping("/UsersRegister")
	@ResponseBody
	public boolean UsersRegister(@Valid @RequestBody users u, Errors errors) {
		if (errors.getErrorCount() > 0)
			return false;
		return us.insert(u);
	}

	// 普通用户登录
	@RequestMapping("/UsersLogin")
	@ResponseBody
	public String[] UsersLogin(@RequestBody users u, HttpSession session) {
		String[] list = new String[1];
		list[0] = us.selectNP(u, session, false);
		return list;
	}

	// 用户退出
	@RequestMapping("/exit")
	@ResponseBody
	public boolean exit(HttpSession session) {
		users u = (users) session.getAttribute("users");
		if (u != null) {
			System.err.println("当前用户ID为：" + u.getUserid());
			for (int i = 0; i < MyUserList.getOnlineUsername().size(); i++) {
				users uu = (users) MyUserList.getOnlineUsername().get(i).getAttribute("users");
				if (uu != null) {
					System.err.println("存储的用户ID为：" + uu.getUserid());
					if (u.getUserid() == uu.getUserid()) {
						MyUserList.getOnlineUsername().remove(i);
						session.invalidate();
						return true;
					}
				}
			}
		}
		System.out.println("删除失败！");
		return false;
	}
}
