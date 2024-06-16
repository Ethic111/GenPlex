package com.project.service;

import java.util.List;

import com.project.model.CityVo;
import com.project.model.LoginVO;
import com.project.model.PatientVo;
import com.project.model.StateVo;

public interface PatientService {
	
	void save(PatientVo patientVo);
	
	List<PatientVo> search();
	
	Response seachByFilterValue(int value);
	
	
	PatientVo searchById(int id);
	
	PatientVo searchByEmail(String pemail);
	
	

}
