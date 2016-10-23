package com.econny.webapp.OxygenEntity;
/*service entity to identify the service permission*/
public class OauthServiceEntity {

	String id;
	String roleId;
	String description;

	public OauthServiceEntity(String id, String roleId, String description) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
