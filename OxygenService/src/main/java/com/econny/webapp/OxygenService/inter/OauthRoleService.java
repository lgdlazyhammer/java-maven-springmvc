package com.econny.webapp.OxygenService.inter;

import java.util.List;
import java.util.Map;

import com.econny.webapp.OxygenEntity.OauthRoleEntity;

public interface OauthRoleService {
	
	public void save(OauthRoleEntity oauthRoleEntity);
	
	public void saveBatch(Map<String, Object> map);
	
	public void delete(OauthRoleEntity oauthRoleEntity);
	
	public void update(OauthRoleEntity oauthRoleEntity);
	
	public List<OauthRoleEntity> findList(OauthRoleEntity oauthRoleEntity); 
	
}
