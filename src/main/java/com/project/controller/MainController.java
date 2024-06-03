package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping(value = { "/", "login" })
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@GetMapping(value = "admin/index")
	public ModelAndView adminIndex() {
		return new ModelAndView("admin/index");
	}

	@GetMapping(value = "doctor/index")
	public ModelAndView doctorIndex() {
		return new ModelAndView("doctor/index");
	}

	@GetMapping(value = "patient/index")
	public ModelAndView patientIndex() {
		return new ModelAndView("patient/index");
	}
}
