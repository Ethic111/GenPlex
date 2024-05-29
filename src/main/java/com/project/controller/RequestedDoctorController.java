package com.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVo;
import com.project.model.DegreeVo;
import com.project.model.RequestedDoctorVo;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.DegreeService;
import com.project.service.RequestedDoctorService;
import com.project.service.Response;
import com.project.service.StateService;

@RestController
public class RequestedDoctorController {

	private RequestedDoctorService doctorRequestService;
	private StateService stateService;
	private CityService cityService;
	private DegreeService degreeService;

	public RequestedDoctorController(RequestedDoctorService doctorRequestService, StateService stateService,
			CityService cityService, DegreeService degreeService) {
		this.doctorRequestService = doctorRequestService;
		this.stateService = stateService;
		this.cityService = cityService;
		this.degreeService = degreeService;
	}

	@GetMapping(value = "registerDoctor")
	public ModelAndView registerDoctor() {
		List<StateVo> stateList = this.stateService.search();
		List<CityVo> cityList = this.cityService.search();
		List<DegreeVo> degreeList = this.degreeService.search();

		return new ModelAndView("admin/doctorRegistration", "RequestedDoctorVo", new RequestedDoctorVo())
				.addObject("stateList", stateList).addObject("cityList", cityList).addObject("degreeList", degreeList);
	}

	@PostMapping(value = "insertRequestedDoctor")
	public ModelAndView insertRequestedDoctor(@ModelAttribute RequestedDoctorVo requestedDoctorVo) {
		this.doctorRequestService.save(requestedDoctorVo);
		return new ModelAndView("redirect:registerDoctor");
	}

	@GetMapping(value = "doctors")
	public ModelAndView requestedDoctors() {
		List<RequestedDoctorVo> reqDoctorList = this.doctorRequestService.search();
		return new ModelAndView("admin/viewRequestedDoctors", "reqDoctorList", reqDoctorList);
	}


	@PostMapping(value = "doctorRejected")
	public ModelAndView doctorRejected(@RequestParam("id") int id , @RequestParam("reason") String reason) {

		RequestedDoctorVo reqDoctorVO = this.doctorRequestService.seachById(id);
		this.doctorRequestService.doctorRejected(id,reason,reqDoctorVO);
		return new ModelAndView("redirect:doctors");
	}

	@GetMapping(value = "doctorAccepted")
	public ModelAndView doctorAccepted(@RequestParam("id") int id) {

		RequestedDoctorVo reqDoctorVO = this.doctorRequestService.seachById(id);
		this.doctorRequestService.doctorApproved(id, reqDoctorVO);
		return new ModelAndView("redirect:doctors");
	}

	@PostMapping(value = "filterDoctors")
	public ModelAndView filterDoctors(@ModelAttribute RequestedDoctorVo requestedDoctorVo) {
		return new ModelAndView("admin/viewRequestedDoctors");
	}

	@GetMapping(value = "filterReviewStatus/{review}", produces = "application/json")
	public ResponseEntity<Response> filterReviewStatus(@PathVariable("review") String review) {

		Response response = this.doctorRequestService.filterByReviewStatus(review);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
