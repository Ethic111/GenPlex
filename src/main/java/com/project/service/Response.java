package com.project.service;

import java.util.List;

import com.project.model.DoctorVO;

public class Response {
	
	
	private Object Body;
	private boolean status;
	private String message;
	
	public Object getBody() {
		return Body;
	}
	public void setBody(Object body) {
		Body = body;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
