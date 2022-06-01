package com.ssafy.happyhouse.model;

import java.util.List;

public class QnaDetailDto {
	private int qnano;
	private String userid;
	private String username;
	private String title; 
	private String content;
	private int hit;
	private List<ReplyDto> replies;
	private String regtime;
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public List<ReplyDto> getReplies() {
		return replies;
	}
	public void setReplies(List<ReplyDto> replies) {
		this.replies = replies;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	
}
