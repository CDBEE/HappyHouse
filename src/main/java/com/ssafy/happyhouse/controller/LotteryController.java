package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.LotteryDetailResponseDto;
import com.ssafy.happyhouse.model.LotteryResponseDto;
import com.ssafy.happyhouse.model.MapBoundDto;
import com.ssafy.happyhouse.model.service.LotteryService;

@RestController
@RequestMapping("/lottery")
public class LotteryController {
	
	@Autowired
	private LotteryService lotteryService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getLotteryList(MapBoundDto mapBoundDto) throws Exception{		
		return new ResponseEntity<List<LotteryResponseDto>>(lotteryService.lotteryList(mapBoundDto), HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<?> getLotteryDetail(@RequestParam("lotteryId") int lotteryId) throws Exception{
		return new ResponseEntity<LotteryDetailResponseDto>(lotteryService.lotteryDetail(lotteryId), HttpStatus.OK);		
	}
}
