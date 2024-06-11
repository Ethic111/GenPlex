package com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.DoctorVO;
import com.project.service.DoctorService;
import com.project.utils.BaseMethod;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	private DoctorService doctorRequestService;

	public MainController(DoctorService doctorRequestService) {
		this.doctorRequestService = doctorRequestService;
	}

	@GetMapping(value = { "/", "login" })
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public String viewUserDetails(ModelMap model, HttpServletResponse response, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			request.getSession().invalidate();
			request.getSession().setAttribute("tempStatus", "success");
			request.getSession().setAttribute("statusText", "Logout Successfully!");
		}
		return "login";
	}

	@GetMapping(value = "admin/index")
	public ModelAndView adminIndex() {
		return new ModelAndView("admin/index");
	}

	@GetMapping(value = "doctor/index")
	public ModelAndView doctorIndex() {
		String doctorUN = BaseMethod.getUsername();
		DoctorVO doctorvo = this.doctorRequestService.searchByUn(doctorUN);
		if (doctorvo.isProfileStatus() == false) {
			return new ModelAndView("doctor/completeProfile", "doctorvo", doctorvo);
		} else {
			return new ModelAndView("doctor/index");
		}
	}

	@GetMapping(value = "patient/index")
	public ModelAndView patientIndex() {
		return new ModelAndView("patient/index");
	}

	@RequestMapping(value = "403", method = RequestMethod.GET)
	public ModelAndView load403() {

		return new ModelAndView("login");
	}

}
