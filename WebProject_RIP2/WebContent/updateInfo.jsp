<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
.birth {
	display: block;
	float: left;
	line-height: 250%;
	margin: 0px;
	padding: 0px;
}
</style>
<title>Future Imperfect by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="assets_join/css/main.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

	<!-- Wrapper -->
	<div id="wrapper" style="padding: 0px, 0px, 0px, 0px;">

		<!-- Header -->
		<header id="header">
			<h1>
				<a href="#">Future Imperfect</a>
			</h1>
			<nav class="links">
				<ul>
					<li><a href="#">Lorem</a></li>
					<li><a href="#">Ipsum</a></li>
					<li><a href="#">Feugiat</a></li>
					<li><a href="#">Tempus</a></li>
					<li><a href="#">Adipiscing</a></li>
				</ul>
			</nav>
			<nav class="main">
				<ul>
					<li class="search"><a class="fa-search" href="#search">Search</a>
						<form id="search" method="get" action="#">
							<input type="text" name="query" placeholder="Search" />
						</form></li>
					<li class="menu"><a class="fa-bars" href="#menu">Menu</a></li>
				</ul>
			</nav>
		</header>

		<!-- Menu -->
		<section id="menu">

			<!-- Search -->
			<section>
				<form class="search" method="get" action="#">
					<input type="text" name="query" placeholder="Search" />
				</form>
			</section>

			<!-- Links -->
			<section>
				<ul class="links">
					<li><a href="#">
							<h3>Lorem ipsum</h3>
							<p>Feugiat tempus veroeros dolor</p>
					</a></li>
					<li><a href="#">
							<h3>Dolor sit amet</h3>
							<p>Sed vitae justo condimentum</p>
					</a></li>
					<li><a href="#">
							<h3>Feugiat veroeros</h3>
							<p>Phasellus sed ultricies mi congue</p>
					</a></li>
					<li><a href="#">
							<h3>Etiam sed consequat</h3>
							<p>Porta lectus amet ultricies</p>
					</a></li>
				</ul>
			</section>

			<!-- Actions -->
			<section>
				<ul class="actions vertical">
					<li><a href="#" class="button big fit">Log In</a></li>
				</ul>
			</section>

		</section>
	</div>
	<!-- Main -->
	<div>
		<form action="update.do" method="post"> <!-- 세부속성 설정부탁드려요.  -->
			<table id="joinInfo">
				<tr id="info_content">
					<td colspan="2" id="joinMain">회원정보수정</td>
				</tr>
				<tr id="info_content">
					<td id="info_text">변경하실 비밀번호</td>
					<td><input type="password" name="pw" required
						disabled="disabled"></td>
				</tr>
				<tr id="info_content">
					<td id="info_text">비밀번호 재확인</td>
					<td><input type="password" name="repw" required
						disabled="disabled"></td>
				</tr>
				<tr id="info_content">
					<td id="info_text">이름</td>
					<td><input type="text" name="name" required
						disabled="disabled"></td>
				</tr>
				<tr id="info_content">
					<td id="info_text">휴대전화번호</td>
					<td><input type="tel" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}"
						placeholder="예) ###-####-####" name="tel" required="required"
						disabled="disabled"></td>
				</tr>
				<tr id="info_content">
					<td id="info_text">이메일</td>
					<td><input type="email" name="email" required
						disabled="disabled"></td>
				</tr>
				<tr id="info_content">
					<%-- <td id="info_text" disabled="disabled">비밀번호 힌트</td>
					<td>
						<%
							DAO dao = DAO.getDAO();
							ArrayList<hintVO> hintList = dao.getHintList();
							request.setAttribute("hintList", hintList);
						%> <!-- 비밀번호 힌트 리스트 --> <select class="selectBox" name="hintList"
						onchange="getIndex()">
							<c:forEach items="${hintList}" var="i">
								<option name="hint_index">${i.getHint() }</option>
							</c:forEach> --%>
					</select>
					</td>
					<input type="hidden" name="hintIdx" value="0">
					<!-- 선택된 select의 인덱스 값을 담을 보이지 않는 inputTag. 일종의 변수로 쓴다. -->
				</tr>
				<tr id="info_content">
					<td id="info_text" disabled="disabled">답 :</td>
					<td><input type="text" name="answer" placeholder="답을 입력해주세요."
						required disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="2" id="submit"><input type="submit" value="제출완료">
					</td>
				</tr>
			</table>
		</form>


		<script type="text/javascript">
			function chkID() {
				var inputId = document.f.id.value;
				if (inputId != "") {

					$.ajax({

						url : "ChkId.do",
						data : "inputId=" + inputId,

						success : function(result) {

							if (result == 'true') { //false가 반환되면 중복 아이디가 있는 것.
								var f = document.f;
								f.pw.disabled = false;
								f.repw.disabled = false;
								f.name.disabled = false;
								f.tel.disabled = false;
								f.email.disabled = false;
								f.year.disabled = false;
								f.month.disabled = false;
								f.day.disabled = false;
								f.gender.disabled = false; //중복 패스하면 아래의 입력창들이 활성화된다.
								f.answer.disabled = false;
							}
						}
					});
				}
			}
			function getIndex() {
				var f = document.f;
				f.hintIdx.value = f.hintList.selectedIndex;
			}
			function check() {
				var birth = document.f.year.value + document.f.month.value
						+ document.f.day.value;
				document.f.birth.value = birth;
				if (document.f.pw.value != document.f.repw.value) {
					alert("비밀번호를 확인해주세요.");
					return false;
				}
				return true;
			}
		</script>

	</div>
	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>

</body>
</html>