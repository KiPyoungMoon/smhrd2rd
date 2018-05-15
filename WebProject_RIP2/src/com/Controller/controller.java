package com.Controller;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.JoinService;
import com.servlet.Join_whetherService;
import com.servlet.LoginService;
import com.servlet.chkIdService;
import com.servlet.command;
import com.servlet.id_findService;
import com.servlet.logoutService;
import com.servlet.messageService;

@WebServlet("*.do")
public class controller extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Controller 호출");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		System.out.println(path);
		String command = uri.substring(path.length() + 1);
		System.out.println("최종 : " + command);

		request.setCharacterEncoding("euc-kr");

		command com = null;

		if (command.equals("Join.do")) {
			com = new JoinService();
		} else if (command.equals("Join_whether.do")) {
			com = new Join_whetherService();
		} else if (command.equals("Login.do")) {
			com = new LoginService();
		} else if (command.equals("Id_find.do")) {
			com = new id_findService();
		} else if (command.equals("Logout.do")) {
			com = new logoutService();
		} else if (command.equals("pw_find.do")) {
			// com = new pw_findService();
		} else if (command.equals("SendMessage.do")) {
			com = new messageService();
		} else if (command.equals("ChkId.do")) {
			String id = request.getParameter("id");
			System.out.println("chkid : " + id);
			com = new chkIdService();
		}
		com.excute(request, response);

	}
}
