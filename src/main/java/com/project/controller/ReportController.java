package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;
import com.project.model.ReportTypeVo;
import com.project.model.ReportVo;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.DegreeService;
import com.project.service.DoctorService;
import com.project.service.PatientDoctorMappingService;
import com.project.service.PatientService;
import com.project.service.ReportService;
import com.project.service.ReportTypeService;
import com.project.service.Response;
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
	private PatientDoctorMappingService patientDoctorMappingService;

	public ReportController(ReportService reportService, StateService stateService, CityService cityService,
			PatientService patientService, DoctorService doctorService, ReportTypeService reportTypeService,
			BaseMethod basemethod, PatientDoctorMappingService patientDoctorMappingService) {
		this.reportService = reportService;
		this.stateService = stateService;
		this.cityService = cityService;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.reportTypeService = reportTypeService;
		this.basemethod = basemethod;
		this.patientDoctorMappingService = patientDoctorMappingService;
	}

	@GetMapping(value = "doctor/reports")
	public ModelAndView doctorreports() {
		String doctorun = BaseMethod.getUsername();
		List<ReportVo> reportList = this.reportService.searchByDoctor(doctorun);
		List<CityVo> cityList = this.cityService.search();
		List<StateVo> stateList = this.stateService.search();
		List<PatientDoctorMappingVO> patientDoctorList = this.patientDoctorMappingService.searchByDoctor(doctorun);
		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();

		System.out.println(reportList);
		return new ModelAndView("doctor/viewReports", "reportList", reportList)
				.addObject("patientDoctorList", patientDoctorList).addObject("stateList", stateList)
				.addObject("cityList", cityList).addObject("reportTypeList", reportTypeList);
	}

	@GetMapping(value = "admin/reports")
	public ModelAndView adminreports() {

		List<ReportVo> reportList = this.reportService.search();
		List<CityVo> cityList = this.cityService.search();
		List<StateVo> stateList = this.stateService.search();
		List<DoctorVO> doctorList = this.doctorService.searchAcceptedDoctors();
		List<PatientVo> patientList = this.patientService.search();
		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();

		System.out.println(reportList);
		return new ModelAndView("admin/viewReports", "reportList", reportList).addObject("patientList", patientList)
				.addObject("doctorList", doctorList).addObject("stateList", stateList).addObject("cityList", cityList)
				.addObject("reportTypeList", reportTypeList);
	}

	@GetMapping(value = "doctor/addReport")
	public ModelAndView addReport() {

		List<StateVo> stateList = this.stateService.search();
		List<CityVo> cityList = this.cityService.search();

		String doctorun = BaseMethod.getUsername();
		List<PatientDoctorMappingVO> patientDoctorMappingList = this.patientDoctorMappingService
				.searchByDoctor(doctorun);

		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();

		return new ModelAndView("doctor/addReport", "ReportVo", new ReportVo()).addObject("stateList", stateList)
				.addObject("cityList", cityList).addObject("patientDoctorMappingList", patientDoctorMappingList)
				.addObject("reportTypeList", reportTypeList);
	}

	@GetMapping(value = "patient/reports")
	public ModelAndView patientReports() {

		List<ReportVo> reportList = this.reportService.searchByPatient(BaseMethod.getUsername());
		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
		List<DoctorVO> doctorList = this.doctorService.searchAcceptedDoctors();

		return new ModelAndView("patient/viewReports", "reportList", reportList)
				.addObject("reportTypeList", reportTypeList).addObject("doctorList", doctorList);

	}

	@PostMapping(value = "doctor/saveReport")
	public ModelAndView saveReport(@Valid @ModelAttribute ReportVo reportVo, BindingResult result,
			@RequestParam("reportFile") MultipartFile reportFile, HttpServletRequest request,
			@RequestParam String patientEmail) {

		// String doctorUsername = BaseMethod.getUsername();
		// DoctorVO doctorvo = this.doctorService.searchByUn(doctorUsername);

		// PatientDoctorMappingVO pdVo =
		// this.patientDoctorMappingService.searchByEmail(patientEmail,doctorvo);

		System.out.println("Insert Report-" + patientEmail);

		ReportVo reportvo = new ReportVo();
		reportvo.setId(reportVo.getId());
		reportvo.setDate(reportVo.getDate());
		reportvo.setSummary(reportVo.getSummary());
		reportvo.setCityvo(this.cityService.seachById(reportVo.getCityvo().getId()));
		reportvo.setStatevo(this.stateService.seachById(reportVo.getStatevo().getId()));
		reportvo.setReporttypevo(this.reportTypeService.searchById(reportVo.getReporttypevo().getId()));
		// reportvo.setPatientdoctorvo(pdVo);

		if (result.hasErrors()) {
			System.out.println(result.getObjectName());
			System.out.println(result.getAllErrors());
			List<StateVo> stateList = this.stateService.search();
			List<CityVo> cityList = this.cityService.search();
			String doctorun = BaseMethod.getUsername();
			List<PatientDoctorMappingVO> patientDoctorMappingList = this.patientDoctorMappingService
					.searchByDoctor(doctorun);
			List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
			return new ModelAndView("doctor/addReport", "ReportVo", reportVo).addObject("stateList", stateList)
					.addObject("cityList", cityList).addObject("patientDoctorMappingList", patientDoctorMappingList)
					.addObject("reportTypeList", reportTypeList);
		}

		this.reportService.save(reportvo, reportFile, request, patientEmail);
		return new ModelAndView("redirect:reports");
	}

	@GetMapping(value = "doctor/api/cities")
	@ResponseBody
	public ResponseEntity<List<CityVo>> apiCities(@RequestParam("state") int stateId) {
		System.out.println(stateId);
		List<CityVo> cityList = this.cityService.getCitiesByStateID(stateId);
		return ResponseEntity.ok(cityList);

	}

	@GetMapping(value = "filterReports", produces = "application/json")
	public ResponseEntity<Response> filterReports(@RequestParam(value = "cityState") int cityState,
			@RequestParam(value = "patientDoctor") int patientDoctor,
			@RequestParam(value = "reportType") int reportType) {

		Response response = this.reportService.searchFilter(cityState, patientDoctor, reportType);
		response.setStatus(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "doctorFilterReports", produces = "application/json")
	public ResponseEntity<Response> doctorFilterReports(@RequestParam(value = "cityState") int cityState,
			@RequestParam(value = "patientDoctor") int patientDoctor,
			@RequestParam(value = "reportType") int reportType) {

		Response response = this.reportService.searchDoctorReportFilter(cityState, patientDoctor, reportType);
		response.setStatus(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "patientFilterReports", produces = "application/json")
	public ResponseEntity<Response> patientFilterReports(@RequestParam(value = "patientDoctor") int patientDoctor,
			@RequestParam(value = "reportType") int reportType) {

		Response response = this.reportService.searchPatientReportFilter(patientDoctor, reportType);
		response.setStatus(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
