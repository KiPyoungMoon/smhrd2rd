package com.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DAO {

	private static DAO dao;
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	private DAO() {

	}

	public static DAO getDAO() {
		if (dao == null) {
			dao = new DAO();
		}
		return dao;
	}

	public void getConn() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		InputStream in = this.getClass().getResourceAsStream("../../../../db.properties");

		Properties p = new Properties();
		p.load(in);

		String url = p.getProperty("url");
		String dbid = p.getProperty("dbid");
		String dbpw = p.getProperty("dbpw");

		System.out.println("url : " + url);
		System.out.println("dbid : " + dbid);
		System.out.println("dbpw : " + dbpw);

		conn = DriverManager.getConnection(url, dbid, dbpw);
		if (conn != null) {
			System.out.println("연결 성공");
		} else {
			System.out.println("연결 실패");
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int join(String id, String email, String pw, String name, String tel, String birth, String gender,
			int hint_index, String answer) {
		int cnt = 0;
		try {
			getConn();

			String sql = "insert into member values(?,?,?,?,?,?,?,?,?)";

			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, email);
			pst.setString(3, pw);
			pst.setString(4, name);
			pst.setString(5, tel);
			pst.setString(6, birth);
			pst.setString(7, gender);
			pst.setInt(8, hint_index + 1);
			pst.setString(9, answer);
			cnt = pst.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원가입 업데이트 성공했다.");
			} else {
				System.out.println("회원가입 업데이트 실패했다.");
			}

			close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	public String Join_whether(String email, String tel) throws Exception {
		// 가입여부 확인하는 메소드.
		getConn();
		String sql = "select id from Member where email=? or tel=? ";
		pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, tel);
		rs = pst.executeQuery(); // 데이터를 가져오기 때문에 개수를 원하는 것이 아님
		String User_id = "";
		if (rs.next()) {
			User_id = rs.getString(1);
		}
		close();
		return User_id;
	}

	public String login(String id) throws Exception {
		getConn();
		String sql = "select pw from Member where id=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		rs = pst.executeQuery(); // 데이터를 가져오기 때문에 개수를 원하는 것이 아님
		String User_pw = "";
		if (rs.next()) {
			User_pw = rs.getString(1);

		}
		close();
		return User_pw;
	}

	public ArrayList<hintVO> getHintList() throws Exception {
		// 비밀번호 찾기 할 때 쓰는 힌트질문의 리스트를 가져오는 메소드.
		this.getConn();
		String sql = "select * from rip.hint";
		ArrayList<hintVO> hintList = new ArrayList<hintVO>();
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			int hint_index = rs.getInt(1);
			String hint = rs.getString(2);
			hintList.add(new hintVO(hint, hint_index));
		}
		this.close();
		return hintList;
	}

	public String Select_id(String email, String tel) throws Exception {
		// 아이디 찾기하는 메소드.
		String User_id = this.Join_whether(email, tel);
		return User_id;
	}

	public void Update_pw(String id) throws Exception {
		getConn();
		String sql = "update member set pw = ? where id=?";
		pst = conn.prepareStatement(sql);

		this.close();
	}

	public int messageInsert(String id, String writer, String content) throws Exception {
		this.getConn();
		String sql = "insert into guestbook values(?, ?, ?, to_char(sysdate,'yyyy-mm-dd'))";
		pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		pst.setString(2, writer);
		pst.setString(3, content);
		int cnt = pst.executeUpdate();

		this.close();

		return cnt;
	}

	public void getMemorial(String id) {
		// 망자 1인의 정보를 망자의 id값으로 검색한다.
		// 검색에서 이름이나 이메일 등의 정보로 검색을 하고 해당 검색 내용을 클릭해 망자 페이지로 넘어오면
		// 그 때, id값을 이 메소드로 넘겨줘야 한다.
	}

	public void memorialList() throws Exception {
		// 망자 전체 리스트를 가져온다.
		this.getConn();
		String sql = "select * from memorial order by desc";
		pst = conn.prepareStatement(sql);
		// 아직 메모리얼에 들어갈 모든 값을 확정하지 않았으므로 일단 비워둠.

	}

	public ArrayList<guestBookVO> getMessage(String id) throws Exception {
		// 방명록에서 메시지를 읽어오는 메소드.
		this.getConn();
		String sql = "select writer, context,visittime from guestbook where id=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		rs = pst.executeQuery();
		ArrayList<guestBookVO> msgList = new ArrayList<guestBookVO>();

		while (rs.next()) {
			String writer = rs.getString(1);
			String context = rs.getString(2);
			String visittime = rs.getString(3);
			msgList.add(new guestBookVO(writer, context, visittime));
		}
		this.close();
		return msgList;
	}

	public String chkID(String id) throws Exception {
		// 중복 아이디 확인하는 메소드.
		this.getConn();
		String chkId = "true";
		String sql = "select id from member where id =?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		rs = pst.executeQuery();
		if (rs.next()) {
			chkId = "false";
			// DB에서 중복값이 검색되면 false를 반환한다.
		}
		return chkId;
	}
}
