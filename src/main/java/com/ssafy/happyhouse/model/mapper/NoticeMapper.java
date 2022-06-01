package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.NoticeDetailDto;
import com.ssafy.happyhouse.model.NoticeDto;


@Mapper
public interface NoticeMapper {
	List<NoticeDto> getList() throws Exception;
	NoticeDetailDto getNotice(int noticeno) throws Exception;
	void updateHit(int noticeno)throws Exception;
	int updateNotice(NoticeDto noticeDto)throws Exception;
	int deleteNotice(int noticeno)throws Exception;
	int createNotice(NoticeDto noticeDto)throws Exception;
}
