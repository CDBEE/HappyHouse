package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeDetailDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	// 리스트
	@GetMapping("/list")
	public ResponseEntity<?> getList() throws Exception{
		return new ResponseEntity<List<NoticeDto>>(noticeService.getList(), HttpStatus.OK);
	}
	
	// detail
	@GetMapping("{noticeno}")
	public ResponseEntity<?> getNotice(@PathVariable int noticeno) throws Exception{
		NoticeDetailDto dto = noticeService.getNotice(noticeno);
		return new ResponseEntity<NoticeDetailDto>(dto, HttpStatus.OK);
	}
	
	// create
	@PostMapping("/create")
	public ResponseEntity<?> createNotice(@RequestBody NoticeDto noticeDto)
			throws Exception{
		System.out.println(noticeDto.toString());
		if(noticeService.createNotice(noticeDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	
	// update
	@PutMapping("{noticeno}")
	public ResponseEntity<?> updateNotice(@RequestBody NoticeDto noticeDto) throws Exception{
		System.out.println(noticeDto.toString());
		if(noticeService.updateNotice(noticeDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	
	// delete
	@DeleteMapping("{noticeno}")
	public ResponseEntity<?> deleteNotice(@PathVariable int noticeno) throws Exception{
		if(noticeService.deleteNotice(noticeno)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}

}
