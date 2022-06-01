package com.ssafy.happyhouse.model;

public class AptListInfoDto {	
	private String aptcode;
	private String address;	
	private String apartmentName;
	private String dealAmount;	
	private String area;
	private String lat;
	private String lng;
	public String getAptcode() {
		return aptcode;
	}
	public void setAptcode(String aptcode) {
		this.aptcode = aptcode;
	}
	public String getAddress() {
		return address;
	}
	
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {		
		this.area = area.split("\\.")[0];
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "AptListInfoDto [aptcode=" + aptcode + ", address=" + address + ", dealAmount=" + dealAmount + ", area="
				+ area + ", lat=" + lat + ", lng=" + lng + "]";
	}

	
	
	
}
