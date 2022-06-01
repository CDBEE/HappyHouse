package com.ssafy.happyhouse.model;

import java.util.List;

public class HouseInfoDealDto {
	private String dongname;
	private String address;
	private String apartmentName;
	private String buildYear;
	private String jibun;
	private String dealAmount;
	private List<HouseDealListDto> dealList;
	
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public List<HouseDealListDto> getDealList() {
		return dealList;
	}
	public void setDealList(List<HouseDealListDto> dealList) {
		for(HouseDealListDto dto : dealList) {
			String dealMonth = dto.getDealMonth().length() == 1 ? "0"+dto.getDealMonth() : dto.getDealMonth();
			String dealDay = dto.getDealDay().length() == 1 ? "0"+dto.getDealDay() : dto.getDealDay();
			String dealDate = dto.getDealYear() + "." + dealMonth + "." + dealDay;
			dto.setDealDate(dealDate);
		}
		
		
		this.dealList = dealList;
	}
	@Override
	public String toString() {
		return "HouseInfoDealDto [dongname=" + dongname + ", address=" + address + ", apartmentName=" + apartmentName
				+ ", buildYear=" + buildYear + ", jibun=" + jibun + ", dealAmount=" + dealAmount + ", dealList="
				+ dealList.toString() + "]";
	}
	
	
	
}
