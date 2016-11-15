package com.econny.webapp.OxygenEnum;

public enum NodeFileType {
	TypeOne (0,"下载文件"),
	TypeTwo (1,"显示图片");
	
	private final int code;//标记号
	private final String description;//描述
	
	NodeFileType(int code, String description){
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
