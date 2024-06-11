package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.DoctorVO;
import com.project.service.DegreeService;
import com.project.service.DoctorProfileService;
import com.project.service.DoctorService;

@Controller
public class DoctorProfileController {

	private DoctorProfileService doctorProfileService;
	private DoctorService doctorService;

	public DoctorProfileController(DoctorProfileService doctorProfileService, DoctorService doctorService) {
		this.doctorProfileService = doctorProfileService;
		this.doctorService = doctorService;
	}

	@PostMapping(value = "doctor/addProfile")
	public ModelAndView saveCircular(@ModelAttribute DoctorVO doctorvo, MultipartFile govermentId,
			MultipartFile addressProof, MultipartFile doctorCertificate, MultipartFile profilePicture,
			HttpServletRequest request) {

		int id = doctorvo.getId();

		DoctorVO doctorVO = this.doctorService.seachById(id);

		doctorVO.setAge(doctorvo.getAge());
		doctorVO.setProfileStatus(true);
		DoctorVO new_doctorvo = this.doctorProfileService.saveProfile(govermentId, addressProof, doctorCertificate, profilePicture, request,doctorvo);
		
		doctorVO.setCertificatePath(new_doctorvo.getCertificatePath());
		doctorVO.setAddressProofPath(new_doctorvo.getAddressProofPath());
		doctorVO.setGovernmentIdPath(new_doctorvo.getGovernmentIdPath());
		doctorVO.setProfilePhotoPath(new_doctorvo.getProfilePhotoPath());
		
		System.out.println(doctorVO.getAddressProofPath());
		
		this.doctorService.save(doctorVO);

		return new ModelAndView("redirect:/doctor/profile");
	}
}
