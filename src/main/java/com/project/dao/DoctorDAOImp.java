package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.enums.DoctorReviewStatus;
import com.project.model.DoctorVO;
import com.project.model.StateVo;

@Repository
public class DoctorDAOImp implements DoctorDAO {

	SessionFactory sessionFactory;

	public DoctorDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(DoctorVO requestedDoctorVo) {

		Session session = this.sessionFactory.getCurrentSession();
		// System.out.println(requestedDoctorVo.getStateName());
		session.saveOrUpdate(requestedDoctorVo);
	}

	public List<DoctorVO> search() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from DoctorVO where status = true and reviewStatus =:reviewStatus");
		q.setParameter("reviewStatus", DoctorReviewStatus.NOT_REVIEWED);
		List<DoctorVO> searchList = q.list();
		return searchList;
	}
	
	public List<DoctorVO> searchAcceptedDoctors() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from DoctorVO where status = true and reviewStatus =:reviewStatus");
		q.setParameter("reviewStatus", DoctorReviewStatus.ACCEPTED);
		List<DoctorVO> searchList = q.list();
		return searchList;
	}

	public DoctorVO seachById(int id) {

		DoctorVO reqDocvo = new DoctorVO();
		Session session = this.sessionFactory.getCurrentSession();
		// System.out.println(id);
		Query q = session.createQuery("from DoctorVO where status = true and id = " + id);
		List<DoctorVO> searchList = q.list();

		if (searchList.size() > 0) {
			reqDocvo = searchList.get(0);
		}
		return reqDocvo;
	}

	public List<DoctorVO> filterByReviewStatus(DoctorReviewStatus review) {

		Session session = this.sessionFactory.getCurrentSession();
		Query q = null;

		if (review.equals(DoctorReviewStatus.ACCEPTED)) {

			q = session.createQuery("from DoctorVO where status =true and reviewStatus =:reviewstatus");

		} else if (review.equals(DoctorReviewStatus.NOT_REVIEWED)) {

			q = session.createQuery("from DoctorVO where reviewStatus =:reviewstatus");

		} else if (review.equals(DoctorReviewStatus.REJECTED)) {
			q = session.createQuery("from DoctorVO where status =false and reviewStatus =:reviewstatus");

		} else if (review.equals(null)) {
			q = session.createQuery("from DoctorVO where status = true");
			System.out.print("all doctors");
		}
		q.setParameter("reviewstatus", review);
		List<DoctorVO> searchList = q.list();
		return searchList;
	}

	public DoctorVO searchByUn(String logUsername) {
		DoctorVO reqDocvo = new DoctorVO();
		Session session = this.sessionFactory.getCurrentSession();
		// System.out.println(id);
		Query q = session.createQuery(
				"from DoctorVO where status = true and reviewStatus =:reviewAccepted and email =:loginun");
		q.setParameter("reviewAccepted", DoctorReviewStatus.ACCEPTED);
		q.setParameter("loginun", logUsername);
		List<DoctorVO> searchList = q.list();

		if (searchList.size() > 0) {
			reqDocvo = searchList.get(0);
		}
		return reqDocvo;
	}

}
