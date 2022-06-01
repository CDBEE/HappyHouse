package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.QnaDetailDto;
import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.ReplyDto;

@Mapper
public interface QnaMapper {
	List<QnaDto> getList(String userid) throws Exception;
	List<QnaDto> getListByAdmin() throws Exception;
	QnaDetailDto getQna(int qnano) throws Exception;
	void updateHit(int qnano) throws Exception;
	int updateQna(QnaDto qnaDto) throws Exception;
	int deleteQna(int qnano) throws Exception;
	int createQna(QnaDto qnaDto) throws Exception;
	
	List<ReplyDto> getReplies(int qnano) throws Exception;
	int updateReply(ReplyDto replyDto) throws Exception;
	int deleteReply(int replyno) throws Exception;
	int createReply(ReplyDto replyDto) throws Exception;
}
