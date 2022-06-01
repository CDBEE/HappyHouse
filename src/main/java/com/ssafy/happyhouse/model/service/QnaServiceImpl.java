package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.QnaDetailDto;
import com.ssafy.happyhouse.model.QnaDto;
import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService{
	@Autowired
	private QnaMapper qnaMapper;

	@Override
	public List<QnaDto> getList(String userid) throws Exception {
		return qnaMapper.getList(userid);
	}

	@Override
	public List<QnaDto> getListByAdmin() throws Exception{
		return qnaMapper.getListByAdmin();
	}
	
	@Override
	public QnaDetailDto getQna(int qnano) throws Exception {
		QnaDetailDto dto = qnaMapper.getQna(qnano);
		
		if(dto != null) {
			qnaMapper.updateHit(qnano);
			dto.setHit(dto.getHit() + 1);
		}
		
		return dto;
	}

	@Override
	@Transactional
	public boolean updateQna(QnaDto qnaDto) throws Exception {
		return qnaMapper.updateQna(qnaDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQna(int qnano) throws Exception {
		return qnaMapper.deleteQna(qnano) == 1;		
	}

	@Override
	@Transactional
	public boolean createQna(QnaDto qnaDto) throws Exception {
		return qnaMapper.createQna(qnaDto) == 1;		
	}

	@Override
	public List<ReplyDto> getReplies(int qnano) throws Exception {
		return qnaMapper.getReplies(qnano);
	}

	@Override
	@Transactional
	public boolean updateReply(ReplyDto replyDto) throws Exception {
		return qnaMapper.updateReply(replyDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteReply(int replyno) throws Exception {
		return qnaMapper.deleteReply(replyno) == 1;
	}

	@Override
	@Transactional
	public boolean createReply(ReplyDto replyDto) throws Exception {
		return qnaMapper.createReply(replyDto) == 1;
	}
	
	
	
}
