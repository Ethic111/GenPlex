package com.project.service;

import java.util.List;

import com.project.model.CityVo;
import com.project.model.PatientVo;
import com.project.model.StateVo;

public interface PatientService {
	
	void save(PatientVo patientVo);
	
	List<PatientVo> search();
	
	PatientVo searchById(int id);

}
