package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DAO;

public class chkIdService implements command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAO dao = DAO.getDAO();
		String chkId = "";
		String inputId = request.getParameter("inputId");

		try {
			chkId = dao.chkID(inputId);
			// System.out.println(chkId.length());//������� ���ڿ� ���̰� ����.
			response.getWriter().print(chkId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
