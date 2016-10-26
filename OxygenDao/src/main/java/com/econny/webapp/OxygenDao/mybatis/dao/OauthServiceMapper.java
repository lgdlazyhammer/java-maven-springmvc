package com.econny.webapp.OxygenDao.mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.OauthServiceEntity;

@Repository
public interface OauthServiceMapper {
	
	public void save(OauthServiceEntity oauthServiceEntity);
	
	public void delete(OauthServiceEntity oauthServiceEntity);
	
	public void update(OauthServiceEntity oauthServiceEntity);
	
	public List<OauthServiceEntity> findList(OauthServiceEntity oauthServiceEntity);
}
