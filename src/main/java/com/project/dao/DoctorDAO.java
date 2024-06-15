package com.project.dao;

import java.util.List;

import com.project.enums.DoctorReviewStatus;
import com.project.model.DoctorVO;

public interface DoctorDAO {
	
	void save(DoctorVO requestedDoctorVo);
	
	List<DoctorVO> search();
	
	List<DoctorVO> searchAcceptedDoctors();

	DoctorVO seachById(int id);
	
	List<DoctorVO> filterByReviewStatus(DoctorReviewStatus review);
	
	DoctorVO searchByUn(String logUsername);

}
