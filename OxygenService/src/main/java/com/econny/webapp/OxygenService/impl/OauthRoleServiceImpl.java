package com.econny.webapp.OxygenService.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthRoleMapper;
import com.econny.webapp.OxygenEntity.OauthRoleEntity;
import com.econny.webapp.OxygenService.inter.OauthRoleService;

@Service
@Transactional(readOnly=true)
public class OauthRoleServiceImpl implements OauthRoleService {

	@Autowired
	OauthRoleMapper oauthRoleMapper;

	@Transactional(readOnly=false)
	public void save(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		oauthRoleMapper.save(oauthRoleEntity);
	}

	@Transactional(readOnly=false)
	public void delete(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		oauthRoleMapper.delete(oauthRoleEntity);
	}

	@Transactional(readOnly=false)
	public void update(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		oauthRoleMapper.update(oauthRoleEntity);
	}

	public List<OauthRoleEntity> findList(OauthRoleEntity oauthRoleEntity) {
		// TODO Auto-generated method stub
		return oauthRoleMapper.findList(oauthRoleEntity);
	}

	@Transactional(readOnly=false)
	public void saveBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		oauthRoleMapper.saveBatch(map);
	}

}
