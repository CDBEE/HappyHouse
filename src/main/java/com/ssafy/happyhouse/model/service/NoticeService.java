package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDetailDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeService {

	//getList
	List<NoticeDto> getList() throws Exception;
	
	//getNotice (detail page)
	NoticeDetailDto getNotice(int noticeno) throws Exception;
	
	//createNotice
	boolean createNotice(NoticeDto noticeDto) throws Exception;
	
	//updateNotice
	boolean updateNotice(NoticeDto noticeDto) throws Exception;
	
	//deleteNotice
	boolean deleteNotice(int noticeno) throws Exception;
	
}
