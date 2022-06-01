package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	
	int idCheck(String id) throws Exception;
	
	boolean registerUser(UserDto user) throws Exception;
	
	UserDto loginUser(UserDto user) throws Exception;
	
	UserDto userInfo(String id) throws Exception;
	
	UserDto findPassword(UserDto user) throws Exception;
	
	boolean updateUser(UserDto user) throws Exception;
	
	boolean deleteUser(UserDto user) throws Exception;	
}
