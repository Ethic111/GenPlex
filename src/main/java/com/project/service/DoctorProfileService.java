package com.project.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.project.model.DoctorVO;

public interface DoctorProfileService {
	
	DoctorVO saveProfile(MultipartFile govermentId, MultipartFile addressProof, MultipartFile doctorCertificate,
			MultipartFile profilePicture, HttpServletRequest request,DoctorVO doctorVo);

}
