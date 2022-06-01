package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.QnaDetailDto;
import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.ReplyDto;

public interface QnaService {
	List<QnaDto> getList(String userid) throws Exception;
	List<QnaDto> getListByAdmin() throws Exception;
	QnaDetailDto getQna(int qnano) throws Exception;
	boolean updateQna(QnaDto qnaDto) throws Exception;
	boolean deleteQna(int qnano) throws Exception;
	boolean createQna(QnaDto qnaDto) throws Exception;
	
	List<ReplyDto> getReplies(int qnano) throws Exception;
	boolean updateReply(ReplyDto replyDto) throws Exception;
	boolean deleteReply(int replyno) throws Exception;
	boolean createReply(ReplyDto replyDto) throws Exception;
}
