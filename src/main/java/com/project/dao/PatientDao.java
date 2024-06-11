package com.project.dao;

import java.util.List;

import com.project.model.PatientVo;

public interface PatientDao {
	
	void save(PatientVo patientVo);
	
	List<PatientVo> search();
	
	PatientVo searchById(int id);

}
