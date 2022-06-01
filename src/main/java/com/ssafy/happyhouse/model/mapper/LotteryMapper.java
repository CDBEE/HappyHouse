package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.LotteryDetailResponseDto;
import com.ssafy.happyhouse.model.LotteryResponseDto;
import com.ssafy.happyhouse.model.MapBoundDto;

@Mapper
public interface LotteryMapper {
	
	List<LotteryResponseDto> lotteryList(MapBoundDto mapBoundDto) throws Exception;
	LotteryDetailResponseDto lotteryDetail(int lotteryId) throws Exception;
	
}
