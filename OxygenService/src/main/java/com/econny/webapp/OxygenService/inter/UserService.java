package com.econny.webapp.OxygenService.inter;

import java.util.List;
import java.util.Map;

import com.econny.webapp.OxygenEntity.UserEntity;

public interface UserService {

	public UserEntity getUserById();
	
	public List<UserEntity> qryUserByPage(Map<String, Object> map);
	
	public void insertUser(Map<String, Object> map);
	
	public void insertUserBatch(Map<String, Object> map);
	
	public void updateUserById(Map<String, Object> map);
	
	public void deleteUserById(Map<String, Object> map);
}
