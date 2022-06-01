package com.ssafy.happyhouse.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	
	int idCheck(String id) throws Exception;
	
	int registerUser(UserDto user) throws Exception;
	
	UserDto loginUser(UserDto user) throws Exception;
	
	UserDto userInfo(String id) throws Exception;
	
	UserDto findPassword(UserDto user) throws Exception;
	
	int updateUser(UserDto user) throws Exception;
	
	int deleteUser(UserDto user) throws Exception;
	
	
}
