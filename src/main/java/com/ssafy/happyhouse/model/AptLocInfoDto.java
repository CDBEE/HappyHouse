package com.ssafy.happyhouse.model;

public class AptLocInfoDto {
	private String address;
	private String location;
	private String dealamount;
	private String lat;
	private String lng;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDealamount() {
		return dealamount;
	}
	public void setDealamount(String dealamount) {
		this.dealamount = dealamount;
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
		return "AptLocInfoDto [address=" + address + ", location=" + location + ", dealamount=" + dealamount + ", lat="
				+ lat + ", lng=" + lng + "]";
	}
	
	
}
