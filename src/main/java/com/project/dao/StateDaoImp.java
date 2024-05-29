package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.StateVo;


@Repository
public class StateDaoImp implements StateDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void save(StateVo stateVo) {

		Session session = this.sessionFactory.getCurrentSession();
		System.out.println(stateVo.getStateName());
		session.saveOrUpdate(stateVo);
	}
	
	public List<StateVo> search(){
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from StateVo where status = true");
		List<StateVo> searchList = q.list();
		return searchList;
	}
	
	public StateVo seachById(int id) {

		StateVo statevo = new StateVo();
		Session session = this.sessionFactory.getCurrentSession();
//		System.out.println(id);
		Query q = session.createQuery("from StateVo where status = true and id = " + id);
		List<StateVo> searchList = q.list();

		if (searchList.size() > 0) {
			statevo = searchList.get(0);
		}
		return statevo;
	}
}
