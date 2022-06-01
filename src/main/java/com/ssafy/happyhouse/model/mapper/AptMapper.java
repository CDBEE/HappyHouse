package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface AptMapper {
	List<AptListInfoDto> aptlist(MapBoundDto mapBoundDto) throws Exception;
	List<AptLocInfoDto> aptDongList(MapBoundDto mapBoundDto) throws Exception;
	List<AptLocInfoDto> aptGugunList(MapBoundDto mapBoundDto) throws Exception;
	List<AptLocInfoDto> aptSidoList(MapBoundDto mapBoundDto) throws Exception;
	HouseInfoDealDto aptdetail(String aptcode) throws Exception;
	List<HouseDealListDto> aptDealList(String aptcode) throws Exception;
	List<AddressDto> addsearch(String addString) throws Exception;
	int checkInterest(InterestDto interestDto) throws Exception;
	int addInterest(InterestDto interestDto) throws Exception;
	List<InterestListDto> interestList(String userid) throws Exception;
	int deleteInterest(InterestDto interestDto) throws Exception;
	List<SubwayResponseDto> subwayList(MapBoundDto mapBoundDto)throws Exception;
}
