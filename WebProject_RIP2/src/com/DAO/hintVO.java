package com.DAO;

public class hintVO {

	String hint;
	int hint_index;

	public hintVO(String hint, int hint_index) {
		this.hint = hint;
		this.hint_index = hint_index;
	}

	public String getHint() {
		return hint;
	}

	public int getHint_index() {
		return hint_index;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public void setHint_index(int hint_index) {
		this.hint_index = hint_index;
	}

}
