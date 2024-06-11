package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVo;
import com.project.model.PatientVo;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.PatientService;
import com.project.service.StateService;

@Controller
public class PatientController {

	private PatientService patientService;
	private CityService cityservice;
	private StateService stateService;

	public PatientController(PatientService patientService, CityService cityservice, StateService stateService) {
		this.patientService = patientService;
		this.cityservice = cityservice;
		this.stateService = stateService;
	}

	@PostMapping("doctor/insertPatient")
	public ResponseEntity<PatientVo> insertPatient(@RequestParam String patientEmail, @RequestParam int stateId,
			@RequestParam int cityId) {
		
		System.out.println(patientEmail);
		PatientVo patientVo = new PatientVo();

		patientVo.setEmail(patientEmail);

		StateVo statevo = new StateVo();
		statevo = this.stateService.seachById(stateId);

		CityVo cityvo = new CityVo();
		cityvo = this.cityservice.seachById(cityId);
		
		patientVo.setCityvo(cityvo);
		patientVo.setStatevo(statevo);

		patientService.save(patientVo);

		return ResponseEntity.ok(patientVo);

	}

}
