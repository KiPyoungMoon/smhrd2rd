<%@page import="com.DAO.DAO"%>
<%@page import="com.DAO.guestBookVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		session.setAttribute("id", "thefates82");
	%>
	<form action="SendMessage.do" method="post">
		<div class="field half first">
			<label for="name">Name</label>
			<c:choose>
				<c:when test="${empty id }">
					<input type="text" id="name" name="name" placeholder="�α����� ���ּ���." />
				</c:when>
				<c:otherwise>
					<input type="text" id="name" name="name" placeholder="${id }" />
					<!-- �α��� �� ����� �̸��� �ߵ��� ������ ��. -->
				</c:otherwise>
			</c:choose>
		</div>
		<div class="field half">
			<label for="email">ID</label> <input type="text" id="id" name="id"
				placeholder="�� �������� id" />
		</div>
		<div class="field">
			<label for="message">Message</label>
			<textarea id="message" rows="6" cols="50" name="content"></textarea>
		</div>
		<ul class="actions">
			<li><input type="submit" value="Send Message" /></li>
			<li><input type="reset" value="Clear" /></li>
		</ul>
	</form>

	<%
		String id = (String) session.getAttribute("id");
		ArrayList<guestBookVO> msgList = DAO.getDAO().getMessage(id);
	%>
	<ul class="actions">
		<c:forEach var="i" items="${msgList }" step="1">
			<li>���� : ${i.getContext() }</li>
			<li>�ۼ��� : ${i.getWriter() }</li>
			<li>�ۼ��� : ${i.getVisitTime() }</li>
		</c:forEach>
	</ul>
</body>
</html>