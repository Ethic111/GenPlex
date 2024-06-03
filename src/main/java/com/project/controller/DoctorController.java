package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.enums.DoctorReviewStatus;
import com.project.model.CityVo;
import com.project.model.DegreeVo;
import com.project.model.DoctorVO;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.DegreeService;
import com.project.service.DoctorService;
import com.project.service.Response;
import com.project.service.StateService;


@Controller
public class DoctorController {

	private DoctorService doctorRequestService;
	private StateService stateService;
	private CityService cityService;
	private DegreeService degreeService;

	public DoctorController(DoctorService doctorRequestService, StateService stateService,
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

		return new ModelAndView("doctorRegistration", "DoctorVO", new DoctorVO())
				.addObject("stateList", stateList).addObject("cityList", cityList).addObject("degreeList", degreeList);
	}

	@PostMapping(value = "admin/insertRequestedDoctor")
	public ModelAndView insertRequestedDoctor(@ModelAttribute DoctorVO requestedDoctorVo) {
		requestedDoctorVo.setStatus(true);
		requestedDoctorVo.setReviewStatus(DoctorReviewStatus.NOT_REVIEWED);
		
		this.doctorRequestService.save(requestedDoctorVo);
		return new ModelAndView("redirect:registerDoctor");
	}

	@GetMapping(value = "admin/doctors")
	public ModelAndView requestedDoctors() {
		List<DoctorVO> reqDoctorList = this.doctorRequestService.search();
		return new ModelAndView("admin/viewRequestedDoctors", "reqDoctorList", reqDoctorList);
	}

	@PostMapping(value = "admin/doctorRejected")
	public ModelAndView doctorRejected(@RequestParam("id") int id, @RequestParam("reason") String reason) {

		DoctorVO reqDoctorVO = this.doctorRequestService.seachById(id);
		this.doctorRequestService.doctorRejected(id, reason, reqDoctorVO);
		return new ModelAndView("redirect:doctors");
	}

	@GetMapping(value = "admin/doctorAccepted")
	public ModelAndView doctorAccepted(@RequestParam("id") int id) {

		DoctorVO reqDoctorVO = this.doctorRequestService.seachById(id);
		this.doctorRequestService.doctorApproved(id, reqDoctorVO);
		return new ModelAndView("redirect:doctors");
	}

	@PostMapping(value = "admin/filterDoctors")
	public ModelAndView filterDoctors(@ModelAttribute DoctorVO requestedDoctorVo) {
		return new ModelAndView("admin/viewRequestedDoctors");
	}

	@GetMapping(value = "filterReviewStatus/{review}", produces = "application/json")
	public ResponseEntity<Response> filterReviewStatus(@PathVariable("review") String review) {

		Response response = this.doctorRequestService.filterByReviewStatus(review);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "admin/saveDoctorRegFurtherDetails")
	public ModelAndView saveDoctorRegFurtherDetails(@ModelAttribute DoctorVO requestedDoctorVo,
			HttpSession session) {
		/* System.out.println(requestedDoctorVo.getDoctorName()); */
		int age = requestedDoctorVo.getAge();
		DoctorVO reqDocVo = (DoctorVO) session.getAttribute("doctorInfo");
		reqDocVo.setAge(age);
		this.doctorRequestService.save(reqDocVo);
		session.removeAttribute("doctorInfo");
		return new ModelAndView("redirect:doctor");
	}
}
