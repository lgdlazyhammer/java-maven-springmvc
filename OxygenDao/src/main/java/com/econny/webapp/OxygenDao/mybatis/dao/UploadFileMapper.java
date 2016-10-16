package com.econny.webapp.OxygenDao.mybatis.dao;

import org.springframework.stereotype.Repository;

import com.econny.webapp.OxygenEntity.UploadFileEntity;

@Repository
public interface UploadFileMapper {

	public void insert(UploadFileEntity UploadFileEntity);
	
	public UploadFileEntity getById(UploadFileEntity UploadFileEntity);
}
