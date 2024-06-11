package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.PatientVo;
import com.project.model.ReportTypeVo;

@Repository
public class PatientDaoImp implements PatientDao {

	private SessionFactory sessionFactory;

	public PatientDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(PatientVo patientVo) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(patientVo);

	}

	public List<PatientVo> search() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from PatientVo where status = true");
		List<PatientVo> searchList = q.list();

		return searchList;
	}

	public PatientVo searchById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from PatientVo where status = true and id =" + id);
		List<PatientVo> searchList = q.list();
		PatientVo patientvo = new PatientVo();
		if (searchList.size() > 0) {
			patientvo = searchList.get(0);
		}
		return patientvo;
	}

}
