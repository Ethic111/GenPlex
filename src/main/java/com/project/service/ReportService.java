package com.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.model.PatientDoctorMappingVO;
import com.project.model.ReportVo;

public interface ReportService {
	
	void save(ReportVo reportVo,MultipartFile reportFile,HttpServletRequest request,String email);
	
	void delete(PatientDoctorMappingVO pdvo);
	
	void deleteReport(ReportVo reportVo);

	List<ReportVo> search();
	
	ReportVo searchByReportID(int rid);
	
	List<ReportVo> searchByDoctor(String doctorun);
	
	List<ReportVo> searchByPatient(String patientUn);
	
	Response searchFilter(int cityState,int patientDoctor,int reportType);
	
	Response searchDoctorReportFilter(int cityState,int patientDoctor,int reportType);
	
	Response searchPatientReportFilter(int patientDoctor,int reportType);
	

}
