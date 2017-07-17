package com.javferna.packtpub.mastering.newsNotification.data;

import java.util.Date;

public class News {
	
	public static final int SPORTS=0;
	public static final int WORLD=1;
	public static final int ECONOMIC=2;
	public static final int SCIENCE=3;

	private int category;
	private String txt;
	private Date date;

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
