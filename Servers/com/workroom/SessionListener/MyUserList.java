package com.workroom.SessionListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.util.HttpSessionMutexListener;

import com.workroom.domain.users;

public class MyUserList implements HttpSessionListener {
	private static List<HttpSession> onlinesession = new ArrayList<HttpSession>();// 统计在线人数

	public static List<HttpSession> getOnlineUsername() {
		return onlinesession;
	}

	public static void addUsername(HttpSession session) {
		onlinesession.add(session);
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO 当session创建时
		System.err.println("页面创建了一个session:");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO 当session销毁时
		System.out.println("用户登录超时，移除列表");
		onlinesession.remove(event.getSession());
	}
}
