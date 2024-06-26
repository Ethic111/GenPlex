package com.project.service;

import java.util.List;

import com.project.enums.DoctorReviewStatus;
import com.project.model.DoctorVO;

public interface DoctorService {
	
	void save(DoctorVO requestedDoctorVo);

	List<DoctorVO> search();
	
	List<DoctorVO> searchAcceptedDoctors();

	void doctorApproved(int id, DoctorVO reqDoctvo);
	
	DoctorVO seachById(int id);
	
	void doctorRejected(int id,String reason, DoctorVO reqDoctvo);
	
	Response filterByReviewStatus(String review);
	
	DoctorVO searchByUn(String logUsername);
}
