package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DAO;

@WebServlet("/LoginService")
public class LoginService implements command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = DAO.getDAO();
		String id = request.getParameter("id");

		HttpSession session = request.getSession();
		String pw = request.getParameter("pw");

		try {
			String getpw = dao.login(id);

			if (getpw.equals(pw)) {
				
				session.setAttribute("id", id);
				response.sendRedirect("main.jsp");
				
			} else {
				response.sendRedirect("main.jsp");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
