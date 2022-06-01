package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.AddressDto;
import com.ssafy.happyhouse.model.AptListInfoDto;
import com.ssafy.happyhouse.model.AptLocInfoDto;
import com.ssafy.happyhouse.model.HouseDealListDto;
import com.ssafy.happyhouse.model.HouseInfoDealDto;
import com.ssafy.happyhouse.model.HouseSearchInfoDto;
import com.ssafy.happyhouse.model.InterestDto;
import com.ssafy.happyhouse.model.InterestListDto;
import com.ssafy.happyhouse.model.MapBoundDto;
import com.ssafy.happyhouse.model.SubwayResponseDto;

public interface AptService {

	List<AptListInfoDto> aptlist(MapBoundDto mapBoundDto) throws Exception;
	List<AptLocInfoDto> aptDongList(MapBoundDto mapBoundDto) throws Exception;
	List<AptLocInfoDto> aptGugunList(MapBoundDto mapBoundDto) throws Exception;
	List<AptLocInfoDto> aptSidoList(MapBoundDto mapBoundDto) throws Exception;
	HouseInfoDealDto aptdetail(String aptcode) throws Exception;
	List<AddressDto> addsearch(String addString) throws Exception;
	boolean addInterest(InterestDto interestDto) throws Exception;
	List<InterestListDto> interestList(String userid) throws Exception;
	List<InterestListDto> deleteInterest(InterestDto interestDto) throws Exception;
	
	List<SubwayResponseDto> subwayList(MapBoundDto mapBoundDto) throws Exception;
}
