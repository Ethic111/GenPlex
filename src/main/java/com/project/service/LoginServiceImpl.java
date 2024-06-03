package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.LoginDAO;
import com.project.model.LoginVO;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Override
	public void save(LoginVO loginVO) {
		this.loginDAO.save(loginVO);
	}

	@Override
	public List searchByUserName(String userName) {
		return this.loginDAO.searchByUserName(userName);
	}

}
