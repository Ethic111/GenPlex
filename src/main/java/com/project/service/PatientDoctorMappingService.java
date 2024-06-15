package com.project.service;

import java.util.List;

import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;

public interface PatientDoctorMappingService {
	
	void save(PatientDoctorMappingVO pdvo);
	
	PatientDoctorMappingVO searchByEmail(String email,DoctorVO doctorvo); 
	
	PatientDoctorMappingVO searchById(int pid,String doctorun); 
	
	List<PatientDoctorMappingVO> searchByDoctor(String doctorun);
	
	List<String> getPatientsEmails();
	
	Response seachDoctorFilterValue(int value);

}
