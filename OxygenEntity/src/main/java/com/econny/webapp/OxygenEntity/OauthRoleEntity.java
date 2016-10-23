package com.econny.webapp.OxygenEntity;
/*role entity to specify the role's service permission*/
public class OauthRoleEntity {
	
	private String id;
	private String userId;
	private String description;
	
	public OauthRoleEntity(String id, String userId, String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
