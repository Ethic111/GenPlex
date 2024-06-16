package com.project.service;

import java.util.List;

import com.project.model.LoginVO;

public interface LoginService {

	void save(LoginVO loginVO);

	LoginVO searchByUserName(String userName);

	void delete(LoginVO loginvo);
}
