package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.UserEntity;

@Repository
public interface UserMapper {

	public UserEntity selectUser(int id);
	
	public List<UserEntity> qryUserByPage(Map<String, Object> map);

	public void insertUser(Map<String, Object> map);
	
	public void insertUserBatch(Map<String, Object> map);
	
	public void updateUserById(Map<String, Object> map);
	
	public void deleteUserById(Map<String, Object> map);
}
