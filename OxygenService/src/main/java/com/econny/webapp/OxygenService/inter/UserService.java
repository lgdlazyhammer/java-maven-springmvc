package com.econny.webapp.OxygenService.inter;

import java.util.List;
import java.util.Map;

import com.econny.webapp.OxygenEntity.OauthUserEntity;

public interface UserService {

	public OauthUserEntity getUserById();
	
	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map);
	
	public void insertUser(Map<String, Object> map);
	
	public void insertUserBatch(Map<String, Object> map);
	
	public void updateUserById(Map<String, Object> map);
	
	public void deleteUserById(Map<String, Object> map);
}
