package com.project.dao;

import java.util.List;

import org.hibernate.Session;

import com.project.model.PatientVo;

public interface PatientDao {
	
	Session getSession();
	
	void save(PatientVo patientVo);
	
	List<PatientVo> search();
	
	List<PatientVo> seachByFilterValue(int value);
	

	
	PatientVo searchById(int id);
	
	PatientVo searchByEmail(String pemail);

}
