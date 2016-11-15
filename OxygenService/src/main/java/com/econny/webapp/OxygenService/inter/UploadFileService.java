package com.econny.webapp.OxygenService.inter;

import com.econny.webapp.OxygenEntity.UploadFileEntity;

public interface UploadFileService {
	
	public void insert(UploadFileEntity UploadFileEntity);
	
	public UploadFileEntity getById(UploadFileEntity UploadFileEntity);
	
	public void delete(UploadFileEntity UploadFileEntity, String filePath, String filePathStatic);
}
