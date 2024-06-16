package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.DoctorVO;
import com.project.model.PatientDoctorMappingVO;
import com.project.model.PatientVo;
import com.project.model.ReportTypeVo;
import com.project.model.ReportVo;
import com.project.service.CityService;
import com.project.service.DoctorService;
import com.project.service.PatientDoctorMappingService;
import com.project.service.PatientService;
import com.project.service.ReportService;
import com.project.service.ReportTypeService;
import com.project.service.StateService;
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
	private ReportService reportService;
	private StateService stateService;
	private CityService cityService;
	private PatientService patientService;
	private DoctorService doctorService;
	private ReportTypeService reportTypeService;
	private BaseMethod basemethod;
	private PatientDoctorMappingService patientDoctorMappingService;

	public MainController(DoctorService doctorRequestService, ReportService reportService, StateService stateService,
			CityService cityService, PatientService patientService, DoctorService doctorService,
			ReportTypeService reportTypeService, BaseMethod basemethod,
			PatientDoctorMappingService patientDoctorMappingService) {

		this.doctorRequestService = doctorRequestService;
		this.reportService = reportService;
		this.stateService = stateService;
		this.cityService = cityService;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.reportTypeService = reportTypeService;
		this.basemethod = basemethod;
		this.patientDoctorMappingService = patientDoctorMappingService;
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

		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
		List<ReportVo> reportList = this.reportService.search();
		List<DoctorVO> doctorList = this.doctorService.searchAcceptedDoctors();
		List<PatientVo> patientList = this.patientService.search();
		List<PatientDoctorMappingVO> pdMappingList = this.patientDoctorMappingService.search();

		// Initialize labels and data lists
		List<String> barChartLabels = new ArrayList<>();
		List<Integer> barChartData = new ArrayList<>();

		// Create a map to count the occurrences of each report type
		Map<String, Integer> reportTypeCountMap = new HashMap<>();

		// Initialize the reportTypeCountMap with report type names
		for (ReportTypeVo reportType : reportTypeList) {
			reportTypeCountMap.put(reportType.getReportTypeName(), 0);
		}

		// Count the occurrences of each report type in the reportList
		for (ReportVo report : reportList) {
			String reportTypeName = report.getReporttypevo().getReportTypeName();
			reportTypeCountMap.put(reportTypeName, reportTypeCountMap.get(reportTypeName) + 1);
		}

		// Populate the labels and data lists for the charts
		for (Map.Entry<String, Integer> entry : reportTypeCountMap.entrySet()) {
			barChartLabels.add(entry.getKey());
			barChartData.add(entry.getValue());
		}

		// // Print the labels and data to the console
		// System.out.println("Bar Chart Labels: " + barChartLabels);
		// System.out.println("Bar Chart Data: " + barChartData);
		
		int patientCount = patientList.size();
		int doctorCount = doctorList.size();
		int reportCount = reportList.size();

		ModelAndView modelAndView = new ModelAndView("admin/index");

		modelAndView.addObject("barChartLabels", barChartLabels);
		modelAndView.addObject("barChartData", barChartData);
		modelAndView.addObject("doctorCount", doctorCount);
		modelAndView.addObject("patientCount", patientCount);
		modelAndView.addObject("reportCount", reportCount);

		return modelAndView;
	}

	@GetMapping(value = "doctor/index")
	public ModelAndView doctorIndex() {
		String doctorUN = BaseMethod.getUsername();
		DoctorVO doctorvo = this.doctorRequestService.searchByUn(doctorUN);

		if (!doctorvo.isProfileStatus()) {
			return new ModelAndView("doctor/completeProfile", "doctorvo", doctorvo);
		} else {
			List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
			List<ReportVo> reportList = this.reportService.searchByDoctor(doctorUN);
			List<PatientDoctorMappingVO> pdMappingList = this.patientDoctorMappingService.searchByDoctor(doctorUN);

			// Initialize labels and data lists
			List<String> barChartLabels = new ArrayList<>();
			List<Integer> barChartData = new ArrayList<>();
			List<String> patientReportbarChartLabels = new ArrayList<>();
			List<Integer> patientReportbarChartData = new ArrayList<>();

			// Create maps to count the occurrences
			Map<String, Integer> reportTypeCountMap = new HashMap<>();
			Map<String, Integer> patientReportCountMap = new HashMap<>();

			// Initialize the reportTypeCountMap with report type names
			for (ReportTypeVo reportType : reportTypeList) {
				reportTypeCountMap.put(reportType.getReportTypeName(), 0);
			}

			// Initialize the patientReportCountMap with patient emails
			for (ReportVo report : reportList) {
				patientReportCountMap.put(report.getPatientdoctorvo().getPatientvo().getEmail(), 0);
			}

			// Count the occurrences of each report type in the reportList
			for (ReportVo report : reportList) {
				String reportTypeName = report.getReporttypevo().getReportTypeName();
				reportTypeCountMap.put(reportTypeName, reportTypeCountMap.get(reportTypeName) + 1);
			}

			// Count the occurrences of each report in the reportList for each
			// patient
			for (ReportVo report : reportList) {
				String patientReportName = report.getPatientdoctorvo().getPatientvo().getEmail();
				patientReportCountMap.put(patientReportName, patientReportCountMap.get(patientReportName) + 1);
			}

			// Populate the labels and data lists for the charts
			for (Map.Entry<String, Integer> entry : reportTypeCountMap.entrySet()) {
				barChartLabels.add(entry.getKey());
				barChartData.add(entry.getValue());
			}

			for (Map.Entry<String, Integer> entry : patientReportCountMap.entrySet()) {
				patientReportbarChartLabels.add(entry.getKey());
				patientReportbarChartData.add(entry.getValue());
			}
			
			int patientCount = pdMappingList.size();
			int reportCount = reportList.size();
			
//			System.out.println(patientReportbarChartLabels);
//			System.out.println(patientReportbarChartData);

			// Create and return the ModelAndView
			ModelAndView modelAndView = new ModelAndView("doctor/index");
			modelAndView.addObject("barChartLabels", barChartLabels);
			modelAndView.addObject("barChartData", barChartData);
			modelAndView.addObject("patientReportbarChartLabels", patientReportbarChartLabels);
			modelAndView.addObject("patientReportbarChartData", patientReportbarChartData);
			modelAndView.addObject("patientCount", patientCount);
			modelAndView.addObject("reportCount", reportCount);

			return modelAndView;
		}
	}

	@GetMapping(value = "patient/index")
	public ModelAndView patientIndex() {
		
		List<ReportTypeVo> reportTypeList = this.reportTypeService.search();
		List<ReportVo> reportList = this.reportService.searchByPatient(BaseMethod.getUsername());
//		List<DoctorVO> doctorList = this.doctorService.searchAcceptedDoctors();
//		List<PatientVo> patientList = this.patientService.search();
//		List<PatientDoctorMappingVO> pdMappingList = this.patientDoctorMappingService.s

		// Initialize labels and data lists
		List<String> pieChartLabels = new ArrayList<>();
		List<Integer> pieChartData = new ArrayList<>();
		List<String> barChartLabels = new ArrayList<>();
		List<Integer> barChartData = new ArrayList<>();

		// Create a map to count the occurrences of each report type
		Map<String, Integer> reportTypeCountMap = new HashMap<>();
		Map<String, Integer> doctorReportCountMap = new HashMap<>();

		// Initialize the reportTypeCountMap with report type names
		for (ReportTypeVo reportType : reportTypeList) {
			reportTypeCountMap.put(reportType.getReportTypeName(), 0);
		}
		
		for (ReportVo report : reportList) {
			doctorReportCountMap .put(report.getPatientdoctorvo().getDoctorvo().getEmail(), 0);
		}

		// Count the occurrences of each report type in the reportList
		for (ReportVo report : reportList) {
			String reportTypeName = report.getReporttypevo().getReportTypeName();
			reportTypeCountMap.put(reportTypeName, reportTypeCountMap.get(reportTypeName) + 1);
		}
		
		for (ReportVo report : reportList) {
			String doctUName = report.getPatientdoctorvo().getDoctorvo().getEmail();
			doctorReportCountMap.put(doctUName, doctorReportCountMap.get(doctUName) + 1);
		}

		// Populate the labels and data lists for the charts
		for (Map.Entry<String, Integer> entry : reportTypeCountMap.entrySet()) {
			pieChartLabels.add(entry.getKey());
			pieChartData.add(entry.getValue());
		}
		
		for (Map.Entry<String, Integer> entry : doctorReportCountMap.entrySet()) {
			barChartLabels.add(entry.getKey());
			barChartData.add(entry.getValue());
		}
		
		int doctorCount = barChartData.size();
//		System.out.println(doctorCount);
		
		int reportCount = reportList.size();
//		System.out.println(reportCount);
		

		// // Print the labels and data to the console
//		 System.out.println("Bar Chart Labels: " + barChartLabels);
//		 System.out.println("Bar Chart Data: " + barChartData);

		ModelAndView modelAndView = new ModelAndView("patient/index");

		modelAndView.addObject("pieChartLabels", pieChartLabels);
		modelAndView.addObject("pieChartData", pieChartData);
		modelAndView.addObject("barChartLabels", barChartLabels);
		modelAndView.addObject("barChartData", barChartData);
		modelAndView.addObject("doctorCount", doctorCount);
		modelAndView.addObject("reportCount", reportCount);
		
		return modelAndView;
		
//		return new ModelAndView("patient/index");
	}

	@RequestMapping(value = "403", method = RequestMethod.GET)
	public ModelAndView load403() {

		return new ModelAndView("login");
	}

}
