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

	public List<ReportVo> searchByDoctor(String doctorun) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ReportVo where status = true and patientdoctorvo.doctorvo.email =:email");
		q.setParameter("email", doctorun);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchByPatient(String patientUn) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ReportVo where status = true and patientdoctorvo.patientvo.email =:email");
		q.setParameter("email", patientUn);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterCityState(int cityState) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session
				.createQuery("from ReportVo where status = true and (statevo.id = :value or cityvo.id = :value)");
		q.setParameter("value", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterPatientDoctor(int patientDoctor) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from ReportVo where status = true and (patientdoctorvo.doctorvo.id =:id or patientdoctorvo.patientvo.id =:id)");
		q.setParameter("id", patientDoctor);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterReportType(int reportType) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ReportVo where status = true and reporttypevo.id =:id");
		q.setParameter("id", reportType);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterCSPD(int cityState, int patientDoctor) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true "
				+ "and ((r.patientdoctorvo.doctorvo.id = :pdid or r.patientdoctorvo.patientvo.id = :pdid) "
				+ "and (r.statevo.id = :csid or r.cityvo.id = :csid))";
		Query q = session.createQuery(hql);
		q.setParameter("pdid", patientDoctor);
		q.setParameter("csid", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterCSRT(int cityState, int reportType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and ((r.reporttypevo.id =:reportTypeId) "
				+ "and (r.statevo.id = :csid or r.cityvo.id = :csid))";
		Query q = session.createQuery(hql);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("csid", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterPDRT(int patientDoctor, int reportType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and ((r.reporttypevo.id =:reportTypeId) "
				+ "and (r.patientdoctorvo.doctorvo.id = :pdid or r.patientdoctorvo.patientvo.id = :pdid))";
		Query q = session.createQuery(hql);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("pdid", patientDoctor);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchFilterAll(int cityState, int patientDoctor, int reportType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and ((r.reporttypevo.id =:reportTypeId) "
				+ "and (r.statevo.id = :csid or r.cityvo.id = :csid)"
				+ "and (r.patientdoctorvo.doctorvo.id = :pdid or r.patientdoctorvo.patientvo.id = :pdid))";
		Query q = session.createQuery(hql);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("pdid", patientDoctor);
		q.setParameter("csid", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	////

	public List<ReportVo> searchDoctorFilterCityState(int cityState, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from ReportVo where status = true and patientdoctorvo.doctorvo.id =:doctotid and (statevo.id = :value or cityvo.id = :value)");
		q.setParameter("doctotid", doctorId);
		q.setParameter("value", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchDoctorFilterPatientDoctor(int patientDoctor, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from ReportVo where status = true and patientdoctorvo.doctorvo.id =:doctotid and patientdoctorvo.patientvo.id =:id");
		q.setParameter("doctotid", doctorId);
		q.setParameter("id", patientDoctor);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchDoctorFilterReportType(int reportType, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from ReportVo where status = true and patientdoctorvo.doctorvo.id =:doctotid and reporttypevo.id =:id");
		q.setParameter("doctotid", doctorId);
		q.setParameter("id", reportType);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchDoctorFilterCSPD(int cityState, int patientDoctor, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and r.patientdoctorvo.doctorvo.id =:doctotid "
				+ "and ((r.patientdoctorvo.patientvo.id = :pdid) "
				+ "and (r.statevo.id = :csid or r.cityvo.id = :csid))";
		Query q = session.createQuery(hql);
		q.setParameter("doctotid", doctorId);
		q.setParameter("pdid", patientDoctor);
		q.setParameter("csid", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchDoctorFilterCSRT(int cityState, int reportType, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and r.patientdoctorvo.doctorvo.id =:doctotid "
				+ "and ((r.reporttypevo.id =:reportTypeId) " + "and (r.statevo.id = :csid or r.cityvo.id = :csid))";
		Query q = session.createQuery(hql);
		q.setParameter("doctotid", doctorId);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("csid", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchDoctorFilterPDRT(int patientDoctor, int reportType, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and r.patientdoctorvo.doctorvo.id =:doctotid "
				+ "and ((r.reporttypevo.id =:reportTypeId) " + "and (r.patientdoctorvo.patientvo.id = :pdid))";
		Query q = session.createQuery(hql);
		q.setParameter("doctotid", doctorId);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("pdid", patientDoctor);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchDoctorFilterAll(int cityState, int patientDoctor, int reportType, int doctorId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and r.patientdoctorvo.doctorvo.id =:doctotid "
				+ "and ((r.reporttypevo.id =:reportTypeId) " + "and (r.statevo.id = :csid or r.cityvo.id = :csid)"
				+ "and (r.patientdoctorvo.patientvo.id = :pdid))";
		Query q = session.createQuery(hql);
		q.setParameter("doctotid", doctorId);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("pdid", patientDoctor);
		q.setParameter("csid", cityState);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	//

	public List<ReportVo> searchPatientFilterReports(int patientDoctor, int patientId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from ReportVo where status = true and patientdoctorvo.patientvo.id =:patientid and patientdoctorvo.doctorvo.id =:id");
		q.setParameter("patientid", patientId);
		q.setParameter("id", patientDoctor);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchPatientFilterReportType(int reportType, int patientId) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(
				"from ReportVo where status = true and patientdoctorvo.patientvo.id =:patientid and reporttypevo.id =:id");
		q.setParameter("patientid", patientId);
		q.setParameter("id", reportType);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

	public List<ReportVo> searchPatientFilterPDRT(int patientDoctor, int reportType, int patientId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ReportVo r where r.status = true " + "and r.patientdoctorvo.patientvo.id =:patientid "
				+ "and ((r.reporttypevo.id =:reportTypeId) " + "and (r.patientdoctorvo.doctorvo.id = :doctorid))";
		Query q = session.createQuery(hql);
		q.setParameter("patientid", patientId);
		q.setParameter("reportTypeId", reportType);
		q.setParameter("doctorid", patientDoctor);
		List<ReportVo> searchList = q.list();

		return searchList;
	}

}
