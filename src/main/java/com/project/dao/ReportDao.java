package com.project.dao;

import java.util.List;

import com.project.model.PatientDoctorMappingVO;
import com.project.model.ReportVo;

public interface ReportDao {

	void save(ReportVo reportVo);

	List<ReportVo> search();
	
	ReportVo searchByReportID(int rid);
	
	List<ReportVo> searchByPatientDoctorMap(PatientDoctorMappingVO pdvo);

	List<ReportVo> searchByDoctor(String doctorun);

	List<ReportVo> searchByPatient(String patientUn);

	List<ReportVo> searchFilterCityState(int cityState);

	List<ReportVo> searchFilterPatientDoctor(int patientDoctor);

	List<ReportVo> searchFilterReportType(int reportType);

	List<ReportVo> searchFilterCSPD(int cityState, int patientDoctor);

	List<ReportVo> searchFilterCSRT(int cityState, int reportType);

	List<ReportVo> searchFilterPDRT(int patientDoctor, int reportType);

	List<ReportVo> searchFilterAll(int cityState, int patientDoctor, int reportType);
//
	List<ReportVo> searchDoctorFilterCityState(int cityState,int doctorId);

	List<ReportVo> searchDoctorFilterPatientDoctor(int patientDoctor,int doctorId);

	List<ReportVo> searchDoctorFilterReportType(int reportType,int doctorId);

	List<ReportVo> searchDoctorFilterCSPD(int cityState, int patientDoctor,int doctorId);

	List<ReportVo> searchDoctorFilterCSRT(int cityState, int reportType,int doctorId);

	List<ReportVo> searchDoctorFilterPDRT(int patientDoctor, int reportType,int doctorId);

	List<ReportVo> searchDoctorFilterAll(int cityState, int patientDoctor, int reportType,int doctorId);

	//
	
	List<ReportVo> searchPatientFilterReports(int patientDoctor,int patientId);

	List<ReportVo> searchPatientFilterReportType(int reportType,int patientId);

	List<ReportVo> searchPatientFilterPDRT(int patientDoctor,int reportType,int patientId);
	
}
