package com.econny.webapp.OxygenEntity;

import java.sql.Blob;
import java.util.UUID;

public class UploadFileEntity {

	private String id;// 主键--文件中保存的文件实际名字
	private String fileName;// 文件实际名字
	private String fileType;// 文件类型
	private Long fileSize;// 文件大小
	private String fileContentType;// 文件类型
	private byte[] fileContent; // 文件2进制内容
	private Integer secureLevel;
	
	public UploadFileEntity(){
		this.id = UUID.randomUUID().toString();
	}

	public UploadFileEntity(String id, String fileName, String fileType, Long fileSize, String fileContentType,
			byte[] fileContent, Integer secureLevel) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileContentType = fileContentType;
		this.fileContent = fileContent;
		this.secureLevel = secureLevel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public void setSecureLevel(Integer secureLevel) {
		this.secureLevel = secureLevel;
	}

	public Integer getSecureLevel() {
		return secureLevel;
	}

}
