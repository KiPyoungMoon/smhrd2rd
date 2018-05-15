package com.DAO;

public class guestBookVO {
	private String writer;
	private String context;
	private String visitTime;

	public guestBookVO(String writer, String context, String visitTime) {
		this.writer = writer;
		this.context = context;
		this.visitTime = visitTime;
	}

	public String getWriter() {
		return writer;
	}

	public String getContext() {
		return context;
	}

	public String getVisitTime() {
		return visitTime;
	}

}
