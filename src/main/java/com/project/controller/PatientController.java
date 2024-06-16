package com.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVo;
import com.project.model.DoctorVO;
import com.project.model.LoginVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;
import com.project.model.ReportVo;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.DoctorService;
import com.project.service.LoginService;
import com.project.service.PatientDoctorMappingService;
import com.project.service.PatientService;
import com.project.service.ReportService;
import com.project.service.Response;
import com.project.service.StateService;
import com.project.utils.BaseMethod;

@RestController
public class PatientController {

	private PatientService patientService;
	private CityService cityService;
	private StateService stateService;
	private PatientDoctorMappingService patientDoctorMappingService;
	private LoginService loginService;
	private ReportService reportService;
	private DoctorService doctorService;

	public PatientController(PatientService patientService, CityService cityService, StateService stateService,
			PatientDoctorMappingService patientDoctorMappingService, LoginService loginService,
			ReportService reportService, DoctorService doctorService) {
		this.patientService = patientService;
		this.cityService = cityService;
		this.stateService = stateService;
		this.patientDoctorMappingService = patientDoctorMappingService;
		this.loginService = loginService;
		this.reportService = reportService;
		this.doctorService = doctorService;
	}

	@GetMapping(value = "doctor/patients")
	public ModelAndView doctorpatients() {
		String doctorun = BaseMethod.getUsername();
		List<PatientDoctorMappingVO> patientDoctorList = this.patientDoctorMappingService.searchByDoctor(doctorun);

		List<CityVo> cityList = this.cityService.search();
		List<StateVo> stateList = this.stateService.search();

		return new ModelAndView("doctor/viewPatients", "patientDoctorList", patientDoctorList)
				.addObject("stateList", stateList).addObject("cityList", cityList);
	}

	@GetMapping(value = "admin/patients")
	public ModelAndView adminpatients() {

		List<PatientVo> patientList = this.patientService.search();
		List<CityVo> cityList = this.cityService.search();
		List<StateVo> stateList = this.stateService.search();

		// System.out.println(filterList);
		// System.out.println(stateList);
		/* System.out.println(patientList); */
		return new ModelAndView("admin/viewPatients", "patientList", patientList).addObject("cityList", cityList)
				.addObject("stateList", stateList);
	}

/*	@GetMapping("doctor/addpatient")
	public ModelAndView addpatient() {
		return new ModelAndView("doctor/addpatient");
	}*/

	@PostMapping("doctor/insertPatient")
	public ResponseEntity<PatientVo> insertPatient(@RequestParam String patientEmail, @RequestParam int stateId,
			@RequestParam int cityId) {
		// try {
		System.out.println(patientEmail);
		PatientVo patientVo = new PatientVo();
		patientVo.setEmail(patientEmail);

		StateVo statevo = stateService.seachById(stateId);
		CityVo cityvo = cityService.seachById(cityId);

		//
		// if (statevo == null || cityvo == null) {
		// return ResponseEntity.badRequest().build(); // Return a 400 error if
		// state or city is not found
		// }

		patientVo.setCityvo(cityvo);
		patientVo.setStatevo(statevo);

		this.patientService.save(patientVo);

		return ResponseEntity.ok(patientVo);
		// } catch (Exception e) {
		// e.printStackTrace();
		// return ResponseEntity.status(500).build(); // Return a 500 error on
		// exception
		// }
	}

	@GetMapping(value = "doctor/deletePatient")
	public ModelAndView deletePatient(@RequestParam int pid) {
		PatientVo pvo = this.patientService.searchById(pid);
		PatientDoctorMappingVO pdvo = this.patientDoctorMappingService.searchById(pid, BaseMethod.getUsername());
		LoginVO loginVo = this.loginService.searchByUserName(pvo.getEmail());
		
		this.loginService.delete(loginVo);
		this.patientDoctorMappingService.delete(pdvo);
		this.reportService.delete(pdvo);
		
		return new ModelAndView("redirect:patients");
	}

	@GetMapping(value = "doctor/api/patients")
	@ResponseBody
	public ResponseEntity<List<String>> apiPatients() {
		return ResponseEntity.ok(this.patientDoctorMappingService.getPatientsEmails());

	}

	

	@GetMapping(value = "filterPatients/{value}", produces = "application/json")
	public ResponseEntity<Response> filterReviewStatus(@PathVariable("value") int value) {

		Response response = this.patientService.seachByFilterValue(value);
		response.setStatus(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "doctorFilterPatients/{value}", produces = "application/json")
	public ResponseEntity<Response> doctorFilterPatients(@PathVariable("value") int value) {
		Response response = this.patientDoctorMappingService.seachDoctorFilterValue(value);
		response.setStatus(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
