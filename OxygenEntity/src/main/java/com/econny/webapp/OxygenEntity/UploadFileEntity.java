package com.econny.webapp.OxygenEntity;

import java.util.UUID;

public class UploadFileEntity {

	private String id;// 主键--文件中保存的文件实际名字
	private String fileName;// 文件实际名字
	private String fileType;// 文件类型
	private String fileSize;// 文件大小
	private String fileContentType;// 文件类型
	private Byte fileContent; // 文件2进制内容
	private Integer secureLevel;
	
	public UploadFileEntity(){
		this.id = UUID.randomUUID().toString();
	}

	public UploadFileEntity(String id, String fileName, String fileType, String fileSize, String fileContentType,
			Byte fileContent, Integer secureLevel) {
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

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Byte getFileContent() {
		return fileContent;
	}

	public void setFileContent(Byte fileContent) {
		this.fileContent = fileContent;
	}

	public Integer getSecureLevel() {
		return secureLevel;
	}

	public void setSecureLevel(int secureLevel) {
		this.secureLevel = secureLevel;
	}

}
