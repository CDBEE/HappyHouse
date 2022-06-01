package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}

	@Override
	@Transactional
	public boolean registerUser(UserDto user) throws Exception {
		return userMapper.registerUser(user) == 1;
	}

	@Override
	public UserDto loginUser(UserDto user) throws Exception {
		if(user.getId() == null || user.getPassword() == null) {
			return null;
		}
		return userMapper.loginUser(user);
	}
	
	@Override
	public UserDto userInfo(String id) throws Exception {
		return userMapper.userInfo(id);
	}

	@Override
	public UserDto findPassword(UserDto user) throws Exception {
		return userMapper.findPassword(user);
	}

	@Override
	@Transactional
	public boolean updateUser(UserDto user) throws Exception {
		return userMapper.updateUser(user) == 1;
	}

	@Override
	@Transactional
	public boolean deleteUser(UserDto user) throws Exception {
		return userMapper.deleteUser(user) == 1;
	}

	
}
