package com.econny.webapp.OxygenService.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.econny.webapp.OxygenDao.mybatis.dao.OauthServiceMapper;
import com.econny.webapp.OxygenEntity.OauthServiceEntity;
import com.econny.webapp.OxygenService.inter.OauthServiceService;

@Service
@Transactional(readOnly=true)
public class OauthServiceServiceImpl implements OauthServiceService {

	@Autowired
	OauthServiceMapper oauthServiceMapper;

	@Transactional(readOnly=false)
	public void save(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		oauthServiceMapper.save(oauthServiceEntity);
	}

	@Transactional(readOnly=false)
	public void delete(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		oauthServiceMapper.delete(oauthServiceEntity);
	}

	@Transactional(readOnly=false)
	public void update(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		oauthServiceMapper.update(oauthServiceEntity);
	}

	public List<OauthServiceEntity> findList(OauthServiceEntity oauthServiceEntity) {
		// TODO Auto-generated method stub
		return oauthServiceMapper.findList(oauthServiceEntity);
	}

	@Transactional(readOnly=false)
	public void saveBatch(Map<String, Object> map) {
		// TODO Auto-generated method stub
		oauthServiceMapper.saveBatch(map);
	}

}
