package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.LotteryDetailResponseDto;
import com.ssafy.happyhouse.model.LotteryResponseDto;
import com.ssafy.happyhouse.model.MapBoundDto;
import com.ssafy.happyhouse.model.mapper.LotteryMapper;

@Service
public class LotteryServiceImpl implements LotteryService{

	@Autowired
	private LotteryMapper lotteryMapper;
	
	@Override	
	public List<LotteryResponseDto> lotteryList(MapBoundDto mapBoundDto) throws Exception {		
		return lotteryMapper.lotteryList(mapBoundDto);
	}

	@Override
	public LotteryDetailResponseDto lotteryDetail(int lotteryId) throws Exception {
		return lotteryMapper.lotteryDetail(lotteryId);
	}
	
}
