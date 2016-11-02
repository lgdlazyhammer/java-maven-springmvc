package com.econny.webapp.OxygenEnum;

public enum DelFlag {
	NORMAL ("0","正常--未删除"),
	DELETEd ("1","已删除");

	private final String status;
	private final String description;
	
	private DelFlag(String status, String description) {
		this.status = status;
		this.description = description;
	}
	
	public String getPermission() {
		return status;
	}
	public String getDescription() {
		return description;
	}
}
