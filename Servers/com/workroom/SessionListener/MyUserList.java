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
	private static List<HttpSession> onlinesession = new ArrayList<HttpSession>();// ͳ����������

	public static List<HttpSession> getOnlineUsername() {
		return onlinesession;
	}

	public static void addUsername(HttpSession session) {
		onlinesession.add(session);
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO ��session����ʱ
		System.err.println("ҳ�洴����һ��session:");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO ��session����ʱ
		System.out.println("�û���¼��ʱ���Ƴ��б�");
		onlinesession.remove(event.getSession());
	}
}
