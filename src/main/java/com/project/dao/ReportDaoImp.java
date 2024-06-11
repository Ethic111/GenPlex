package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.ReportVo;

@Repository
public class ReportDaoImp implements ReportDao {

	private SessionFactory sessionFactory;

	public ReportDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(ReportVo reportVo) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(reportVo);
	}

	public List<ReportVo> search() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ReportVo where status = true");
		List<ReportVo> searchList = q.list();

		return searchList;
	}

}
