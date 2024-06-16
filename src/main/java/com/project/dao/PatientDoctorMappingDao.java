package com.project.dao;

import java.util.List;

import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;

public interface PatientDoctorMappingDao {
	
	void save(PatientDoctorMappingVO pdvo);
	
	List<PatientDoctorMappingVO> search();
	
	PatientDoctorMappingVO searchByEmail(String email,DoctorVO doctorvo); 
	
	PatientDoctorMappingVO searchById(int pid,String doctorun); 
	
	List<PatientDoctorMappingVO> searchByDoctor(String doctorun);
	
	List<PatientVo> seachDoctorFilterValue(int value,String doctorun);

}
