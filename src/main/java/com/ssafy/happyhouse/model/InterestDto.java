package com.ssafy.happyhouse.model;

public class InterestDto {
	private String userid;
	private String dongname;
	private String dongcode;
	
	
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		String[] dong = dongname.split(" ");
		this.dongname = dong[dong.length - 1];
	}
	
	
}
