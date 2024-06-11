package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.ReportTypeVo;

@Repository
public class ReportTypeDaoImp implements ReportTypeDao {
	
	private SessionFactory sessionFactory;

	public ReportTypeDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<ReportTypeVo> search() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ReportTypeVo where status = true");
		List<ReportTypeVo> searchList = q.list();

		return searchList;
	}

	public void save(ReportTypeVo reportTypeVo) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(reportTypeVo);
	}

	public ReportTypeVo searchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ReportTypeVo where status = true and id =" + id);
		List<ReportTypeVo> searchList = q.list();
		ReportTypeVo reportTypevo = new ReportTypeVo();
		if (searchList.size() > 0) {
			reportTypevo  = searchList.get(0);
		}
		return reportTypevo;
	}

}
