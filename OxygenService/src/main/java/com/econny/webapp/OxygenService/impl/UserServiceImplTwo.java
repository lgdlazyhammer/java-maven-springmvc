package com.econny.webapp.OxygenService.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthUserMapper;
import com.econny.webapp.OxygenEntity.OauthUserEntity;
import com.econny.webapp.OxygenService.inter.UserService;

@Service
public class UserServiceImplTwo implements UserService {
	
	@Autowired
	OauthUserMapper userMapper;

	public OauthUserEntity getUserById() {
		// TODO Auto-generated method stub
		return userMapper.selectUser("1");
	}

	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.qryUserByPage(map);
	}

	public void insertUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userMapper.insertUser(map);
		
	}

	public void updateUserById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userMapper.updateUserById(map);
	}

	public void deleteUserById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userMapper.deleteUserById(map);
	}

	public void insertUserBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userMapper.insertUserBatch(map);
	}
	
}
