package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.CityVo;

@Repository
public class CityDaoImp implements CityDao {

	SessionFactory sessionFactory;

	public CityDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(CityVo cityVo) {

		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(cityVo);
	}

	public List<CityVo> search() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from CityVo where status = true");
		List<CityVo> searchList = q.list();
		return searchList;
	}

	public CityVo seachById(int id) {

		CityVo cityvo = new CityVo();
		Session session = this.sessionFactory.getCurrentSession();
		// System.out.println(id);
		Query q = session.createQuery("from CityVo where status = true and id = " + id);
		List<CityVo> searchList = q.list();

		if (searchList.size() > 0) {
			cityvo = searchList.get(0);
		}
		return cityvo;
	}

	public List<CityVo> searchCitiesByStateID(int stateId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from CityVo where statevo.id = :stateId");
		q.setParameter("stateId", stateId);
		List<CityVo> searchList = q.list();
		return searchList;

	}

	public CityVo seachByCityId(int cityId) {
		CityVo cityvo = new CityVo();
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from CityVo where id = :cityId");
		q.setParameter("cityId", cityId);
		List<CityVo> searchList = q.list();

		if (searchList.size() > 0) {
			cityvo = searchList.get(0);
		}
		return cityvo;
	}
}
