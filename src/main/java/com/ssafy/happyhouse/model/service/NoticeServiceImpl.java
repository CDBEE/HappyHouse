package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NoticeDetailDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> getList() throws Exception {
		return noticeMapper.getList();
	}

	@Override
	public NoticeDetailDto getNotice(int noticeno) throws Exception {
		NoticeDetailDto dto = noticeMapper.getNotice(noticeno);
		if(dto != null) {
			noticeMapper.updateHit(noticeno);
			dto.setHit(dto.getHit()+1);
		}
		return dto;
	}

	@Override
	@Transactional
	public boolean createNotice(NoticeDto noticeDto) throws Exception {
		return noticeMapper.createNotice(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean updateNotice(NoticeDto noticeDto) throws Exception {
		return noticeMapper.updateNotice(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteNotice(int noticeno) throws Exception {
		return noticeMapper.deleteNotice(noticeno) == 1;
	}
	
	
}
