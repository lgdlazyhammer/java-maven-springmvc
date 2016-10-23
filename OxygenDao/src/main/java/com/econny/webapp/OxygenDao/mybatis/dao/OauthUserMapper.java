package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.OauthUserEntity;

@Repository
public interface OauthUserMapper {

	public OauthUserEntity selectUser(String id);
	
	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map);

	public void insertUser(Map<String, Object> map);
	
	public void insertUserBatch(Map<String, Object> map);
	
	public void updateUserById(Map<String, Object> map);
	
	public void deleteUserById(Map<String, Object> map);
}
