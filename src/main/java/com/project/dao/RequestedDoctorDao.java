package com.project.dao;

import java.util.List;

import com.project.model.DoctorReviewStatus;
import com.project.model.RequestedDoctorVo;

public interface RequestedDoctorDao {
	
	void save(RequestedDoctorVo requestedDoctorVo);
	
	List<RequestedDoctorVo> search();

	RequestedDoctorVo seachById(int id);
	
	List<RequestedDoctorVo> filterByReviewStatus(DoctorReviewStatus review);

}
