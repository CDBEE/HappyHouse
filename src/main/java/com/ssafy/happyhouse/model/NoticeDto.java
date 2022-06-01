package com.ssafy.happyhouse.model;

public class NoticeDto {
	private int noticeno;
	private String content;
	private String title;
	private int hit;
	private String regtime;

	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
	}


	public String getRegtime() {
		return regtime;
	}


	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}


	public int getNoticeno() {
		return noticeno;
	}


	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "NoticeDto [noticeno=" + noticeno + ", content=" + content + ", title=" + title + "]";
	}

}
