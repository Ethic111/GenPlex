package com.project.service;

import java.util.List;

import com.project.model.DoctorReviewStatus;
import com.project.model.RequestedDoctorVo;

public interface RequestedDoctorService {
	
	void save(RequestedDoctorVo requestedDoctorVo);

	List<RequestedDoctorVo> search();

	void doctorApproved(int id, RequestedDoctorVo reqDoctvo);
	
	RequestedDoctorVo seachById(int id);
	
	void doctorRejected(int id,String reason, RequestedDoctorVo reqDoctvo);
	
	Response filterByReviewStatus(String review);
}
