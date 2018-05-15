package com.DAO;

public class memberVO {
	
	String id;
	String email;
	String pw;
	String name;
	String tel;
	String birth;
	String gender;
	int hint_index;
	String answer;
	
	
	public memberVO(String id, String email, String pw, String name, String tel, String birth, String gender,
			int hint_index, String answer) {
		super();
		this.id = id;
		this.email = email;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.birth = birth;
		this.gender = gender;
		this.hint_index = hint_index;
		this.answer = answer;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getHint_index() {
		return hint_index;
	}


	public void setHint_index(int hint_index) {
		this.hint_index = hint_index;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

}
