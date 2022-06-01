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

import com.ssafy.happyhouse.model.QnaDetailDto;
import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("/list/{userid}")
	public ResponseEntity<?> getList(@PathVariable String userid) throws Exception {
		return new ResponseEntity<List<QnaDto>>(qnaService.getList(userid), HttpStatus.OK);
	}
	
	@GetMapping("/listAdmin")
	public ResponseEntity<?> getListByAdmin() throws Exception{
		return new ResponseEntity<List<QnaDto>>(qnaService.getListByAdmin(), HttpStatus.OK);
	}
	
	@GetMapping("{qnano}")
	public ResponseEntity<?> getQna(@PathVariable int qnano) throws Exception{
		QnaDetailDto dto = qnaService.getQna(qnano);
		List<ReplyDto> replies = qnaService.getReplies(qnano);		
		dto.setReplies(replies);
		return new ResponseEntity<QnaDetailDto>(dto, HttpStatus.OK);
	}
	
	@PutMapping("{qnano}")
	public ResponseEntity<?> updateQna(@RequestBody QnaDto qnaDto) throws Exception{
		if(qnaService.updateQna(qnaDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	
	@DeleteMapping("{qnano}")
	public ResponseEntity<?> deleteQna(@PathVariable int qnano) throws Exception{
		if(qnaService.deleteQna(qnano)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	

	@PostMapping("/create")
	public ResponseEntity<?> createQna(@RequestBody QnaDto qnaDto) throws Exception{
		if(qnaService.createQna(qnaDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	
	@PostMapping("/reply/{qnano}")
	public ResponseEntity<?> createReply(@RequestBody ReplyDto replyDto) throws Exception{
		if(qnaService.createReply(replyDto)) {			
			return new ResponseEntity<List<ReplyDto>>(qnaService.getReplies(replyDto.getQnano()),HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	
	@DeleteMapping("/reply/{replyno}")
	public ResponseEntity<?> deleteReply(@PathVariable int replyno) throws Exception{
		if(qnaService.deleteReply(replyno)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
	
	@PutMapping("/reply/{replyno}")
	public ResponseEntity<?> updateReply(@RequestBody ReplyDto replyDto) throws Exception{
		if(qnaService.updateReply(replyDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail",HttpStatus.OK); 
	}
}
