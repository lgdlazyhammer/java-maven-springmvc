package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthRoleMapper;
import com.econny.webapp.OxygenEntity.OauthRoleEntity;
import com.econny.webapp.OxygenService.inter.OauthRoleService;

@Service
public class OauthRoleServiceImpl implements OauthRoleService {

	@Autowired
	OauthRoleMapper oauthRoleMapper;

	public void save(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		oauthRoleMapper.save(oauthRoleEntity);
	}

	public void delete(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		oauthRoleMapper.delete(oauthRoleEntity);
	}

	public void update(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		oauthRoleMapper.update(oauthRoleEntity);
	}

	public List<OauthRoleEntity> findList(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		return oauthRoleMapper.findList(oauthRoleEntity);
	}

}
