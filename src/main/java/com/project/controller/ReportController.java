package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.project.model.CityVo;
import com.project.model.DegreeVo;
import com.project.model.PatientVo;
import com.project.model.ReportTypeVo;
import com.project.model.ReportVo;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.DegreeService;
import com.project.service.DoctorService;
import com.project.service.PatientService;
import com.project.service.ReportService;
import com.project.service.ReportTypeService;
import com.project.service.StateService;
import com.project.utils.BaseMethod;

@Controller
public class ReportController {

	private ReportService reportService;
	private StateService stateService;
	private CityService cityService;
	private PatientService patientService;
	private DoctorService doctorService;
	private ReportTypeService reportTypeService;
	private BaseMethod basemethod;

	public ReportController(ReportService reportService, StateService stateService, CityService cityService,
			PatientService patientService, DoctorService doctorService, ReportTypeService reportTypeService,
			BaseMethod basemethod) {
		this.reportService = reportService;
		this.stateService = stateService;
		this.cityService = cityService;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.reportTypeService = reportTypeService;
		this.basemethod = basemethod;
	}

	@GetMapping(value = "doctor/reports")
	public ModelAndView reports() {
		List<ReportVo> reportList = this.reportService.search();
		System.out.println(reportList);
		return new ModelAndView("doctor/viewReports","reportList",reportList);
	}

	@GetMapping(value = "doctor/addReport")
	public ModelAndView addReport() {

		List<StateVo> stateList = this.stateService.search();
		List<CityVo> cityList = this.cityService.search();
		List<PatientVo> patientList = this.patientService.search();
		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();

		return new ModelAndView("doctor/addReport", "ReportVo", new ReportVo()).addObject("stateList", stateList)
				.addObject("cityList", cityList).addObject("patientList", patientList)
				.addObject("reportTypeList", reportTypeList);
	}

	@PostMapping(value = "doctor/saveReport")
	public ModelAndView saveReport(@Valid @ModelAttribute ReportVo reportVo, BindingResult result,
			@RequestParam("reportFile") MultipartFile reportFile, HttpServletRequest request) {

		// save the patient for new username
		ReportVo reportvo = new ReportVo();
		reportvo.setId(reportVo.getId());
		reportvo.setDate(reportVo.getDate());
		reportvo.setSummary(reportVo.getSummary());
		reportvo.setCityvo(this.cityService.seachById(reportVo.getCityvo().getId()));
		reportvo.setStatevo(this.stateService.seachById(reportVo.getStatevo().getId()));
		reportvo.setReporttypevo(this.reportTypeService.searchById(reportVo.getReporttypevo().getId()));
		reportvo.setPatientvo(this.patientService.searchById(reportVo.getPatientvo().getId()));
		reportvo.setDoctorvo(this.doctorService.searchByUn(BaseMethod.getUsername()));
		
//		System.out.println(reportVo.getSummary());
//		System.out.println(reportVo.getCityvo().getCityName());
//		System.out.println(reportVo.getCityvo().getId());

		if (result.hasErrors()) {
			System.out.println(result.getObjectName());
			System.out.println(result.getAllErrors());
			List<StateVo> stateList = this.stateService.search();
			List<CityVo> cityList = this.cityService.search();
			List<PatientVo> patientList = this.patientService.search();
			List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
			return new ModelAndView("doctor/addReport", "ReportVo", reportVo).addObject("stateList", stateList)
					.addObject("cityList", cityList).addObject("patientList", patientList)
					.addObject("reportTypeList", reportTypeList);
		}

		this.reportService.save(reportvo, reportFile, request);
		return new ModelAndView("redirect:reports");
	}

	@GetMapping(value = "doctor/api/cities")
	@ResponseBody
	public ResponseEntity<List<CityVo>> apiCities(@RequestParam("state") int stateId) {
		System.out.println(stateId);
		List<CityVo> cityList = this.cityService.getCitiesByStateID(stateId);
		return ResponseEntity.ok(cityList);

	}
}
