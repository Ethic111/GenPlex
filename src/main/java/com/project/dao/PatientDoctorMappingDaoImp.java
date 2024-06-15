package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;

@Repository
public class PatientDoctorMappingDaoImp implements PatientDoctorMappingDao {

	private SessionFactory sessionFactory;

	public PatientDoctorMappingDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(PatientDoctorMappingVO pdvo) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(pdvo);
	}

	public PatientDoctorMappingVO searchByEmail(String email, DoctorVO doctorvo) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from PatientDoctorMappingVO where status = true and patientvo.email =:email and doctorvo.id =:id");
		q.setParameter("email", email);
		q.setParameter("id", doctorvo.getId());

		List<PatientDoctorMappingVO> searchList = q.list();
		if (searchList.size() > 0) {
			return searchList.get(0);
		}
		return null;
	}

	public PatientDoctorMappingVO searchById(int pid, String doctorun) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from PatientDoctorMappingVO where status = true and patientvo.id =:pid and doctorvo.email =:email");
		q.setParameter("pid", pid);
		q.setParameter("email", doctorun);

		List<PatientDoctorMappingVO> searchList = q.list();
		PatientDoctorMappingVO pdVo = new PatientDoctorMappingVO();
		if (searchList.size() > 0) {
			pdVo = searchList.get(0);
		}
		return pdVo;
	}

	public List<PatientDoctorMappingVO> searchByDoctor(String doctorun) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from PatientDoctorMappingVO where status = true and doctorvo.email =:email");
		q.setParameter("email", doctorun);
		List<PatientDoctorMappingVO> searchList = q.list();

		return searchList;
	}

	public List<PatientVo> seachDoctorFilterValue(int value, String doctorun) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from PatientDoctorMappingVO where status = true and doctorvo.email =:doctotemail and (patientvo.statevo.id = :value or patientvo.cityvo.id = :value)");
		q.setParameter("doctotemail", doctorun);
		q.setParameter("value", value);
		List<PatientVo> searchList = q.list();
		System.out.println(searchList);
		return searchList;
	}

}
