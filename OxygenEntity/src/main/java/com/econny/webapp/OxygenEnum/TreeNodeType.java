package com.econny.webapp.OxygenEnum;

public enum TreeNodeType {
	LevelOne (0,"跟节点"),
	LevelTwo (1,"叶子节点");
	
	private final int code;//标记号
	private final String description;//描述
	
	TreeNodeType(int code, String description){
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
