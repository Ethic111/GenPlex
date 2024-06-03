package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.LoginVO;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(LoginVO loginVO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(loginVO);

	}

	@Override
	public List searchByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from LoginVO where username= '" + userName + "'");
		List ls = query.list();
		return ls;
	}

}
