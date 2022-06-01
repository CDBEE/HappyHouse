package com.ssafy.happyhouse.model;

// mapper searchByAreaAndDate 에서 파라미터로 사용하는 DTO
public class HouseSearchInfoDto {

	private int dealYear;
	private int dealMonth;
	private String dong;
	
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	
	
}
