package com.econny.webapp.OxygenService.impl;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.econny.webapp.OxygenDao.mybatis.dao.UploadFileMapper;
import com.econny.webapp.OxygenEntity.UploadFileEntity;
import com.econny.webapp.OxygenEnum.FileSecureLevel;
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


	public void delete(UploadFileEntity UploadFileEntity, String filePath, String filePathStatic) {
		// TODO Auto-generated method stub
		UploadFileEntity uploadFileEntityDel = uploadFileMapper.getById(UploadFileEntity);
		if (uploadFileEntityDel.getSecureLevel().equals(FileSecureLevel.LevelOne.getCode())) {
			// do nothing
		} else if (uploadFileEntityDel.getSecureLevel().equals(FileSecureLevel.LevelTwo.getCode())) {
			try {
				FileUtils.deleteDirectory(filePath+uploadFileEntityDel.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (uploadFileEntityDel.getSecureLevel().equals(FileSecureLevel.LevelThree.getCode())) {
			try {
				FileUtils.deleteDirectory(filePathStatic+uploadFileEntityDel.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		uploadFileMapper.delete(uploadFileEntityDel);
	}

}
