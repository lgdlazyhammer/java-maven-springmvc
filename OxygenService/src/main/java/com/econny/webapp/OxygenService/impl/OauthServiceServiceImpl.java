package com.econny.webapp.OxygenService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthServiceMapper;
import com.econny.webapp.OxygenEntity.OauthServiceEntity;
import com.econny.webapp.OxygenService.inter.OauthServiceService;

@Service
public class OauthServiceServiceImpl implements OauthServiceService {

	@Autowired
	OauthServiceMapper oauthServiceMapper;

	public void save(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		oauthServiceMapper.save(oauthServiceEntity);
	}

	public void delete(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		oauthServiceMapper.delete(oauthServiceEntity);
	}

	public void update(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		oauthServiceMapper.update(oauthServiceEntity);
	}

	public List<OauthServiceEntity> findList(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		return oauthServiceMapper.findList(oauthServiceEntity);
	}

}
