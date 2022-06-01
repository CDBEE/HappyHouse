package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.LotteryDetailResponseDto;
import com.ssafy.happyhouse.model.LotteryResponseDto;
import com.ssafy.happyhouse.model.MapBoundDto;

public interface LotteryService {
	List<LotteryResponseDto> lotteryList(MapBoundDto mapBoundDto) throws Exception;
	LotteryDetailResponseDto lotteryDetail(int lotteryId) throws Exception;
}
