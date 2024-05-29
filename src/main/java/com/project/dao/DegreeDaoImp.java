package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.CityVo;
import com.project.model.DegreeVo;

@Repository
public class DegreeDaoImp implements DegreeDao {

	private SessionFactory sessionFactory;

	public DegreeDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<DegreeVo> search() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from DegreeVo where status = true");
		List<DegreeVo> searchList = q.list();

		return searchList;
	}

	public void save(DegreeVo degVo) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(degVo);
	}

	public DegreeVo searchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from DegreeVo where status = true and id =" + id);
		List<DegreeVo> searchList = q.list();
		DegreeVo degreevo = new DegreeVo();
		if (searchList.size() > 0) {
			degreevo = searchList.get(0);
		}
		return degreevo;
	}

}
