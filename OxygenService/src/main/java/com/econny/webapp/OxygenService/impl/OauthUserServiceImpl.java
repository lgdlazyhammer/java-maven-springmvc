package com.econny.webapp.OxygenService.impl;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthUserMapper;
import com.econny.webapp.OxygenEntity.OauthUserEntity;
import com.econny.webapp.OxygenService.inter.OauthUserService;

@Service
public class OauthUserServiceImpl implements OauthUserService {
	
	@Autowired
	OauthUserMapper oauthUserMapper;

	public OauthUserEntity getUserById() {
		return null;
	}

	public List<OauthUserEntity> qryUserByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertUser(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	public void updateUserById(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	public void deleteUserById(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	public void insertUserBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub

	}

	public void save(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		oauthUserMapper.save(oauthUserEntity);
	}

	public void delete(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		oauthUserMapper.delete(oauthUserEntity);
	}

	public void update(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		oauthUserMapper.update(oauthUserEntity);
	}

	public List<OauthUserEntity> findList(OauthUserEntity oauthUserEntity) {
		// TODO Auto-generated method stub
		return oauthUserMapper.findList(oauthUserEntity);
	}

}
