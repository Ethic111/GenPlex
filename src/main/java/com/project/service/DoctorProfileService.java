package com.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.model.DoctorVO;

public interface DoctorProfileService {
	
	DoctorVO saveProfile(MultipartFile govermentId, MultipartFile addressProof, MultipartFile doctorCertificate,
			 HttpServletRequest request,DoctorVO doctorVo);

}
