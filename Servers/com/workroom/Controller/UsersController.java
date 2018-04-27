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

	// ����Ա��¼���������ͨ�û����Ե�¼�����ܾ���¼
	@RequestMapping("/managerLogin")
	@ResponseBody
	public String[] managerLogin(@RequestBody users u, HttpSession session) {
		String[] list = new String[1];
		list[0] = us.selectNP(u, session, true);
		return list;
	}

	// ��ͨ�û���¼
	@RequestMapping("/UserLogin")
	@ResponseBody
	public String[] UserLogin(@RequestBody users u, HttpSession session) {
		String[] list = new String[1];
		System.out.println("ǰ̨�����û���" + u.getUsername());
		System.out.println(u.getPassword());
		list[0] = us.selectUserLogin(u, session);
		return list;
	}

	// �ж��Ƿ��¼�������¼��������true�����򷵻�false����ת����¼ҳ��
	@RequestMapping("/isLogin")
	@ResponseBody
	public boolean managerisLogin(HttpSession session) {
		return session.getAttribute("users") != null ? true : false;
	}

	// ��ȡ����Ա��Ϣ
	@RequestMapping("/getmanagerdata")
	@ResponseBody
	public users getmanagerdata(HttpSession session) {
		return session.getAttribute("users") != null ? (users) session.getAttribute("users") : null;
	}

	// ��ȡ��������
	@RequestMapping("/getUserSize")
	@ResponseBody
	public int getUserSize(HttpSession session) {
		System.out.println("��ǰ��������" + MyUserList.getOnlineUsername().size());
		return MyUserList.getOnlineUsername().size();
	}

	// ��ͨ�û�ע��
	@RequestMapping("/UsersRegister")
	@ResponseBody
	public boolean UsersRegister(@Valid @RequestBody users u, Errors errors) {
		if (errors.getErrorCount() > 0)
			return false;
		return us.insert(u);
	}

	// ��ͨ�û���¼
	@RequestMapping("/UsersLogin")
	@ResponseBody
	public String[] UsersLogin(@RequestBody users u, HttpSession session) {
		String[] list = new String[1];
		list[0] = us.selectNP(u, session, false);
		return list;
	}

	// �û��˳�
	@RequestMapping("/exit")
	@ResponseBody
	public boolean exit(HttpSession session) {
		users u = (users) session.getAttribute("users");
		if (u != null) {
			System.err.println("��ǰ�û�IDΪ��" + u.getUserid());
			for (int i = 0; i < MyUserList.getOnlineUsername().size(); i++) {
				users uu = (users) MyUserList.getOnlineUsername().get(i).getAttribute("users");
				if (uu != null) {
					System.err.println("�洢���û�IDΪ��" + uu.getUserid());
					if (u.getUserid() == uu.getUserid()) {
						MyUserList.getOnlineUsername().remove(i);
						session.invalidate();
						return true;
					}
				}
			}
		}
		System.out.println("ɾ��ʧ�ܣ�");
		return false;
	}
}
