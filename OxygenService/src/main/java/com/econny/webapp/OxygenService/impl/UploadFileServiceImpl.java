package com.econny.webapp.OxygenService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.UploadFileMapper;
import com.econny.webapp.OxygenEntity.UploadFileEntity;
import com.econny.webapp.OxygenService.inter.UploadFileService;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	UploadFileMapper uploadFileMapper;
	
	public void insert(UploadFileEntity UploadFileEntity) {
		// TODO Auto-generated method stub
		uploadFileMapper.insert(UploadFileEntity);
	}
	

	public UploadFileEntity getById(UploadFileEntity UploadFileEntity){
		return uploadFileMapper.getById(UploadFileEntity);
	}

}
