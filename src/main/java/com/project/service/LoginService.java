package com.project.service;

import java.util.List;

import com.project.model.LoginVO;

public interface LoginService {

	void save(LoginVO loginVO);

	List searchByUserName(String userName);
}
