package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DAO;

/**
 * Servlet implementation class Join_whetherService
 */
@WebServlet("/Join_whetherService")
public class Join_whetherService implements command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = DAO.getDAO();
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");

		try {
			String User_id = dao.Join_whether(email, tel);
			System.out.println(User_id);
			if (User_id.equals("")) {
				System.out.println("���̵� �����ϴ�.");
				// ȸ������ ȭ������
				response.sendRedirect("main.jsp");
			} else {
				System.out.println("���̵� �ֽ��ϴ�.");
				// �α��� ȭ������
				response.sendRedirect("main.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
