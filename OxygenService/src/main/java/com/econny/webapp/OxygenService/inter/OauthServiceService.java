package com.econny.webapp.OxygenService.inter;

import java.util.List;

import com.econny.webapp.OxygenEntity.OauthServiceEntity;

public interface OauthServiceService {
	
	public void save(OauthServiceEntity oauthServiceEntity);
	
	public void delete(OauthServiceEntity oauthServiceEntity);
	
	public void update(OauthServiceEntity oauthServiceEntity);
	
	public List<OauthServiceEntity> findList(OauthServiceEntity oauthServiceEntity);
	
}
