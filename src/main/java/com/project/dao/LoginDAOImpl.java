package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.LoginVO;
import com.project.model.PatientVo;

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
	public LoginVO searchByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from LoginVO where username= '" + userName + "'");
		List<LoginVO> searchList = q.list();
	
		if (searchList.size() > 0) {
			return searchList.get(0);
		}
		return null;
	}

}
