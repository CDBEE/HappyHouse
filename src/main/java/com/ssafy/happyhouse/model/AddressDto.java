package com.ssafy.happyhouse.model;

public class AddressDto {
	private String address;
	private double lat;
	private double lng;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLatitude() {
		return lat;
	}
	public void setLatitude(double latitude) {
		this.lat = latitude;
	}
	public double getLongitude() {
		return lng;
	}
	public void setLongitude(double longitude) {
		this.lng = longitude;
	}
	@Override
	public String toString() {
		return "AddressDto [address=" + address + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
