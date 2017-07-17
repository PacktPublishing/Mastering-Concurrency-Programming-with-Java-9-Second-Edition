package com.javferna.packtpub.mastering.eventNotification.data;

import java.util.Date;

public class Event {

	private String msg;
	private String source;
	private Date date;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
