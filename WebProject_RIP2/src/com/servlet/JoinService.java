package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DAO;

public class JoinService implements command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = DAO.getDAO();
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		int hint_index = Integer.parseInt(request.getParameter("hintIdx"));
		String answer = request.getParameter("answer");

		try {
			int cnt = dao.join(id, email, pw, name, tel, birth, gender, hint_index, answer);

			if (cnt > 0) {
				System.out.println("가입 성공");
				response.sendRedirect("signUp.jsp");// 나중에 메인페이지로 이동하도록 해줄 예정.
			} else {
				System.out.println("안들어갔다.");
				response.sendRedirect("signUp.jsp");
			}

		} catch (Exception e) {
			System.out.println("joinservice 예외다.");
		}

	}

}
