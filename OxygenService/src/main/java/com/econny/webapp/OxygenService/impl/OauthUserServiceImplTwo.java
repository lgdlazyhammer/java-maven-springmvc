package com.econny.webapp.OxygenService.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthUserMapper;
import com.econny.webapp.OxygenEntity.OauthUserEntity;
import com.econny.webapp.OxygenService.inter.OauthUserService;

@Service
public class OauthUserServiceImplTwo implements OauthUserService {
	
	@Autowired
	OauthUserMapper oauthUserMapper;

	public OauthUserEntity getUserById() {
		// TODO Auto-generated method stub
		return oauthUserMapper.selectUser("1");
	}

	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return oauthUserMapper.qryUserByPage(map);
	}

	public void insertUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		oauthUserMapper.insertUser(map);
		
	}

	public void updateUserById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		oauthUserMapper.updateUserById(map);
	}

	public void deleteUserById(Map<String, Object> map) {
		// TODO Auto-generated method stub
		oauthUserMapper.deleteUserById(map);
	}

	public void insertUserBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		oauthUserMapper.insertUserBatch(map);
	}

	public void save(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		
	}

	public void update(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		
	}

	public List<OauthUserEntity> findList(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	public Integer checkUserPermission(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
