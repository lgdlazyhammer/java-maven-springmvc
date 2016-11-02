package com.econny.webapp.OxygenEntity;

import java.util.UUID;

/*service entity to identify the service permission*/
public class OauthServiceEntity {

	private String id;
	private String roleId;
	private Integer serviceType;
	private String description;
	private String delFlag;

	public OauthServiceEntity() {
		super();
		this.id = UUID.randomUUID().toString();
		this.delFlag = "0";
	}

	public OauthServiceEntity(String id, String roleId, String description) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.description = description;
		this.delFlag = "0";
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

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
