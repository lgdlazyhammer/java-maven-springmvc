package com.econny.webapp.OxygenService.inter;

import java.util.List;

import com.econny.webapp.OxygenEntity.OauthRoleEntity;

public interface OauthRoleService {
	
	public void save(OauthRoleEntity oauthRoleEntity);
	
	public void delete(OauthRoleEntity oauthRoleEntity);
	
	public void update(OauthRoleEntity oauthRoleEntity);
	
	public List<OauthRoleEntity> findList(OauthRoleEntity oauthRoleEntity); 
	
}
