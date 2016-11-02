package com.econny.webapp.OxygenService.inter;

import java.util.List;
import java.util.Map;

import com.econny.webapp.OxygenEntity.OauthUserEntity;

public interface OauthUserService {

	public OauthUserEntity getUserById();
	
	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map);
	
	public void insertUser(Map<String, Object> map);
	
	public void insertUserBatch(Map<String, Object> map);
	
	public void updateUserById(Map<String, Object> map);
	
	public void deleteUserById(Map<String, Object> map);
	
	//common methods
	public void save(OauthUserEntity oauthUserEntity);
	
	public void saveBatch(Map<String, Object> map);
	
	public void delete(OauthUserEntity oauthUserEntity);
	
	public void update(OauthUserEntity oauthUserEntity);
	
	public List<OauthUserEntity> findList(OauthUserEntity oauthUserEntity); 
	
	//check if user has the service permission
	public Integer checkUserPermission(OauthUserEntity oauthUserEntity);
}
