package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DAO;

public class messageService implements command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = DAO.getDAO();
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("euc-kr");
		String writer = request.getParameter("name");
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		System.out.println(content);

		int cnt = 0;
		try {
			cnt = dao.messageInsert(id, writer, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cnt > 0) {
			System.out.println("메시지 입력 성공");
			response.sendRedirect("message.jsp");
		} else {
			System.out.println("메시지 입력 실패.");
			response.sendRedirect("message.jsp");
		}
	}

}
