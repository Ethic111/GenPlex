package com.project.dao;

import java.util.List;

import com.project.model.LoginVO;

public interface LoginDAO {

	void save(LoginVO loginVO);

	LoginVO searchByUserName(String userName);


}
