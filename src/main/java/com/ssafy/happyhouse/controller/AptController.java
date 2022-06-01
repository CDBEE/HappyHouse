package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AddressDto;
import com.ssafy.happyhouse.model.AptListInfoDto;
import com.ssafy.happyhouse.model.AptLocInfoDto;
import com.ssafy.happyhouse.model.HouseInfoDealDto;
import com.ssafy.happyhouse.model.InterestDto;
import com.ssafy.happyhouse.model.InterestListDto;
import com.ssafy.happyhouse.model.MapBoundDto;
import com.ssafy.happyhouse.model.SubwayResponseDto;
import com.ssafy.happyhouse.model.service.AptService;

@RestController
public class AptController {

	private static final Logger logger = LoggerFactory.getLogger(AptController.class);

	@Autowired
	private AptService aptService;

	// postman: http://localhost/aptlist
	// 위도 경도를 받아서 주변 아파트 리스트를 넘겨주는 메서드
	@GetMapping("/aptlist")
	public ResponseEntity<?> aptlist(MapBoundDto mapBoundDto) throws Exception {
		int zoomLevel = mapBoundDto.getZoomLevel();
		if (zoomLevel < 5) {
			return new ResponseEntity<List<AptListInfoDto>>(aptService.aptlist(mapBoundDto), HttpStatus.OK);
		} else if (zoomLevel < 7) { // 동 기준 조회 ( < 2초)
			return new ResponseEntity<List<AptLocInfoDto>>(aptService.aptDongList(mapBoundDto), HttpStatus.OK);
		} else if (zoomLevel < 10) { // 구군 기준 조회 (4~8초)
			return new ResponseEntity<List<AptLocInfoDto>>(aptService.aptGugunList(mapBoundDto), HttpStatus.OK);
		} else { // 시 기준 조회(8~10초)
			return new ResponseEntity<List<AptLocInfoDto>>(aptService.aptSidoList(mapBoundDto), HttpStatus.OK);

		}
	}

	// postman: http://localhost/aptdetail?aptcode=2
	// 특정 아파트를 받아서 해당 아파트의 거래 정보 및 아파트 정보를 넘겨주는 메서드
	@GetMapping("/aptdetail")
	public ResponseEntity<?> aptdetail(@RequestParam("aptcode") String aptcode) throws Exception {
		HouseInfoDealDto houseDto = aptService.aptdetail(aptcode);
		System.out.println("aptinfo : " + houseDto);
		if (houseDto != null) {
			return new ResponseEntity<HouseInfoDealDto>(houseDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 보낼게 없음
		}
	}

	// postman: http://localhost/addsearch?addString=종로
	// 지역명 검색 : 특정 문자열을 주면 해당 문자열을 포함하는 "시 군 동" 정보와 위도 경도를 반환
	@GetMapping("/addsearch")
	public ResponseEntity<?> addSearch(@RequestParam("addString") String addString) throws Exception {
		List<AddressDto> addList = aptService.addsearch(addString);
		if (addList != null) {
			return new ResponseEntity<List<AddressDto>>(addList, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 보낼게 없음
		}
	}

	@PostMapping("/interest")
	public ResponseEntity<?> addInterest(@RequestBody InterestDto interestDto) throws Exception {
		if (aptService.addInterest(interestDto)) {
			return new ResponseEntity<String>("관심지역이 추가되었습니다", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("이미 존재하는 관심지역입니다.", HttpStatus.OK);
		}
	}

	@GetMapping("/interest")
	public ResponseEntity<?> interestList(@RequestParam("userid") String userid) throws Exception {
		return new ResponseEntity<List<InterestListDto>>(aptService.interestList(userid), HttpStatus.OK);
	}

	@DeleteMapping("/interest")
	public ResponseEntity<?> deleteInterest(InterestDto interestDto) throws Exception {
		List<InterestListDto> list = aptService.deleteInterest(interestDto);
		if (list != null) {
			return new ResponseEntity<List<InterestListDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("삭제 처리 중 문제가 발생하였습니다.", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/subwaylist")
	public ResponseEntity<?> getSubwayList(MapBoundDto mapBoundDto) throws Exception {
		return new ResponseEntity<List<SubwayResponseDto>>(aptService.subwayList(mapBoundDto), HttpStatus.OK);
	}
}
