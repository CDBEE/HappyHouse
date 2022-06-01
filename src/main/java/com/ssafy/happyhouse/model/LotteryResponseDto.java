package com.ssafy.happyhouse.model;

public class LotteryResponseDto {
	private int id;
	//주택명
	private String houseNm;
	//공급위치
	private String hssplyAdres;
	//분양예정 / 종료 / 분양중
	private String status;
	//LAT
	private String lat;
	//LNG
	private String lng;
	
	public int getLotteryId() {
		return id;
	}
	public String getHouseNm() {
		return houseNm;
	}
	public String getHssplyAdres() {
		return hssplyAdres;
	}
	public String getStatus() {
		return status;
	}
	public String getLat() {
		return lat;
	}
	public String getLng() {
		return lng;
	}
	
	public void setLotteryId(int lotteryId) {
		this.id = lotteryId;
	}
	public void setHouseNm(String houseNm) {
		this.houseNm = houseNm;
	}
	public void setHssplyAdres(String hssplyAdres) {
		this.hssplyAdres = hssplyAdres;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}	
	
	
}
