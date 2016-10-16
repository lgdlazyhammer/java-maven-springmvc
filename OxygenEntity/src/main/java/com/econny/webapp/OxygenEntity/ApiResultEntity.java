package com.econny.webapp.OxygenEntity;

public class ApiResultEntity {

	private Boolean success;
	private String result;
	private Integer statusCode;
	private String error;

	public ApiResultEntity(Boolean success, String result, Integer statusCode, String error) {
		super();
		this.success = success;
		this.result = result;
		this.statusCode = statusCode;
		this.error = error;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
