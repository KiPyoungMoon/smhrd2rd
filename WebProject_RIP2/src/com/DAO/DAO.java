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
			System.out.println("���� ����");
		} else {
			System.out.println("���� ����");
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
				System.out.println("ȸ������ ������Ʈ �����ߴ�.");
			} else {
				System.out.println("ȸ������ ������Ʈ �����ߴ�.");
			}

			close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	public String Join_whether(String email, String tel) throws Exception {
		// ���Կ��� Ȯ���ϴ� �޼ҵ�.
		getConn();
		String sql = "select id from Member where email=? or tel=? ";
		pst = conn.prepareStatement(sql);
		pst.setString(1, email);
		pst.setString(2, tel);
		rs = pst.executeQuery(); // �����͸� �������� ������ ������ ���ϴ� ���� �ƴ�
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
		rs = pst.executeQuery(); // �����͸� �������� ������ ������ ���ϴ� ���� �ƴ�
		String User_pw = "";
		if (rs.next()) {
			User_pw = rs.getString(1);

		}
		close();
		return User_pw;
	}

	public ArrayList<hintVO> getHintList() throws Exception {
		// ��й�ȣ ã�� �� �� ���� ��Ʈ������ ����Ʈ�� �������� �޼ҵ�.
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
		// ���̵� ã���ϴ� �޼ҵ�.
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
		// ���� 1���� ������ ������ id������ �˻��Ѵ�.
		// �˻����� �̸��̳� �̸��� ���� ������ �˻��� �ϰ� �ش� �˻� ������ Ŭ���� ���� �������� �Ѿ����
		// �� ��, id���� �� �޼ҵ�� �Ѱ���� �Ѵ�.
	}

	public void memorialList() throws Exception {
		// ���� ��ü ����Ʈ�� �����´�.
		this.getConn();
		String sql = "select * from memorial order by desc";
		pst = conn.prepareStatement(sql);
		// ���� �޸𸮾� �� ��� ���� Ȯ������ �ʾ����Ƿ� �ϴ� �����.

	}

	public ArrayList<guestBookVO> getMessage(String id) throws Exception {
		// ���Ͽ��� �޽����� �о���� �޼ҵ�.
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
		// �ߺ� ���̵� Ȯ���ϴ� �޼ҵ�.
		this.getConn();
		String chkId = "true";
		String sql = "select id from member where id =?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		rs = pst.executeQuery();
		if (rs.next()) {
			chkId = "false";
			// DB���� �ߺ����� �˻��Ǹ� false�� ��ȯ�Ѵ�.
		}
		return chkId;
	}
}
