package com.econny.webapp.OxygenEnum;

public enum ServicePermission {
	FileServicePermission (1,"文件服务权限");

	private final Integer permission;
	private final String description;
	
	private ServicePermission(Integer permission, String description) {
		this.permission = permission;
		this.description = description;
	}
	
	public Integer getPermission() {
		return permission;
	}
	public String getDescription() {
		return description;
	}
}
