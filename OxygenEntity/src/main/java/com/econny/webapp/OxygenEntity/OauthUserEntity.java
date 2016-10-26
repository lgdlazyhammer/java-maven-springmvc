package com.econny.webapp.OxygenEntity;

import java.util.UUID;

public class OauthUserEntity {

	private String id;
	private String name;
	private String password;
	private String gender;
	private String phoneNumber;
	private String email;
	private String description;
	
	public OauthUserEntity(){
		super();
		this.id = UUID.randomUUID().toString();
	}

	public OauthUserEntity(String id, String name, String password, String gender, String phoneNumber, String email,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
