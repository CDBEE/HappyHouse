package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.ssafy.happyhouse.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService{
	
	@Autowired
	private AptMapper aptMapper;

	@Override
	public List<AptListInfoDto> aptlist(MapBoundDto mapBoundDto) throws Exception {
		return aptMapper.aptlist(mapBoundDto);
	}
	
	@Override
	public List<AptLocInfoDto> aptDongList(MapBoundDto mapBoundDto) throws Exception {
		return aptMapper.aptDongList(mapBoundDto);
	}

	@Override
	public List<AptLocInfoDto> aptGugunList(MapBoundDto mapBoundDto) throws Exception {
		return aptMapper.aptGugunList(mapBoundDto);
	}

	@Override
	public List<AptLocInfoDto> aptSidoList(MapBoundDto mapBoundDto) throws Exception {
		return aptMapper.aptSidoList(mapBoundDto);
	}

	@Override
	public HouseInfoDealDto aptdetail(String aptcode) throws Exception {
		HouseInfoDealDto dto = aptMapper.aptdetail(aptcode);
		List<HouseDealListDto> list = aptMapper.aptDealList(aptcode);
		dto.setDealList(list);
		
		return dto;
	}

	@Override
	public List<AddressDto> addsearch(String addString) throws Exception {
		return aptMapper.addsearch(addString);
	}

	@Override
	public boolean addInterest(InterestDto interestDto) throws Exception {
		int check = aptMapper.checkInterest(interestDto);
		if(check > 0) {
			return false;
		}else {
			return aptMapper.addInterest(interestDto) == 1;
		}
		
	}

	@Override
	public List<InterestListDto> interestList(String userid) throws Exception {
		return aptMapper.interestList(userid);
	}

	@Override
	public List<InterestListDto> deleteInterest(InterestDto interestDto) throws Exception {
		if(aptMapper.deleteInterest(interestDto) == 1) {
			return aptMapper.interestList(interestDto.getUserid());
		}
		return null;
	}

	@Override
	public List<SubwayResponseDto> subwayList(MapBoundDto mapBoundDto) throws Exception {
		return aptMapper.subwayList(mapBoundDto);
	}
	
}
