package com.project.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.project.model.DoctorReviewStatus;
import com.project.model.RequestedDoctorVo;
import com.project.model.StateVo;

@Repository
public class RequestedDoctorDaoImp implements RequestedDoctorDao {

	SessionFactory sessionFactory;

	public RequestedDoctorDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(RequestedDoctorVo requestedDoctorVo) {

		Session session = this.sessionFactory.getCurrentSession();
		// System.out.println(requestedDoctorVo.getStateName());
		session.saveOrUpdate(requestedDoctorVo);
	}

	public List<RequestedDoctorVo> search() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from RequestedDoctorVo where status = true and reviewStatus =:reviewStatus");
		q.setParameter("reviewStatus", DoctorReviewStatus.NOT_REVIEWED);
		List<RequestedDoctorVo> searchList = q.list();
		return searchList;
	}

	public RequestedDoctorVo seachById(int id) {

		RequestedDoctorVo reqDocvo = new RequestedDoctorVo();
		Session session = this.sessionFactory.getCurrentSession();
		// System.out.println(id);
		Query q = session.createQuery("from RequestedDoctorVo where status = true and id = " + id);
		List<RequestedDoctorVo> searchList = q.list();

		if (searchList.size() > 0) {
			reqDocvo = searchList.get(0);
		}
		return reqDocvo;
	}

	public List<RequestedDoctorVo> filterByReviewStatus(DoctorReviewStatus review) {

		Session session = this.sessionFactory.getCurrentSession();
		Query q = null;

		if (review.equals(DoctorReviewStatus.ACCEPTED)) {

			q = session.createQuery("from RequestedDoctorVo where status =true and reviewStatus =:reviewstatus");

		} else if (review.equals(DoctorReviewStatus.NOT_REVIEWED)) {

			q = session.createQuery("from RequestedDoctorVo where reviewStatus =:reviewstatus");

		}
		else if (review.equals(DoctorReviewStatus.REJECTED)) {
			q = session.createQuery("from RequestedDoctorVo where status =false and reviewStatus =:reviewstatus");
			
		} else if(review.equals(null)){
			q = session.createQuery("from RequestedDoctorVo where status = true");
			System.out.print("all doctors");
		}
		q.setParameter("reviewstatus", review);
		List<RequestedDoctorVo> searchList = q.list();
		return searchList;
	}

}
