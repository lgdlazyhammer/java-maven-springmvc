package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.OauthRoleEntity;

@Repository
public interface OauthRoleMapper {
	
	public void save(OauthRoleEntity oauthRoleEntity);
	
	public void delete(OauthRoleEntity oauthRoleEntity);
	
	public void update(OauthRoleEntity oauthRoleEntity);
	
	public List<OauthRoleEntity> findList(OauthRoleEntity oauthRoleEntity); 
	
}
