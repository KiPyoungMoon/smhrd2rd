package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DAO;

/**
 * Servlet implementation class id_findService
 */
@WebServlet("/id_findService")
public class id_findService implements command{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao = DAO.getDAO();
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		
		try {
			String User_id = dao.Select_id(email, tel);
			if (!User_id.equals("")) {
				System.out.println("아이디 찾기 성공");
				response.sendRedirect("main.jsp");
			} else {
				System.out.println("아이디가 없습니다");
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
