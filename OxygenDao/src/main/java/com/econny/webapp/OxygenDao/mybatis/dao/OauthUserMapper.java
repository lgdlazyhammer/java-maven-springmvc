package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.OauthRoleEntity;
import com.econny.webapp.OxygenEntity.OauthUserEntity;

@Repository
public interface OauthUserMapper {

	public OauthUserEntity selectUser(String id);
	
	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map);

	public void insertUser(Map<String, Object> map);
	
	public void insertUserBatch(Map<String, Object> map);
	
	public void updateUserById(Map<String, Object> map);
	
	public void deleteUserById(Map<String, Object> map);
	
	//common methods
	public void save(OauthUserEntity oauthUserEntity);
	
	public void delete(OauthUserEntity oauthUserEntity);
	
	public void update(OauthUserEntity oauthUserEntity);
	
	public List<OauthUserEntity> findList(OauthUserEntity oauthUserEntity); 
}
