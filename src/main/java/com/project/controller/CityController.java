package com.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVo;
import com.project.model.StateVo;
import com.project.service.CityService;
import com.project.service.StateService;

@Controller
public class CityController {

	private StateService stateService;
	private CityService cityService;

	public CityController(CityService cityService, StateService stateService) {
		this.cityService = cityService;
		this.stateService = stateService;
	}

	@GetMapping(value = "admin/addCity")
	public ModelAndView addCity() {
		List<StateVo> stateList = this.stateService.search();
		return new ModelAndView("admin/addCity", "CityVo", new CityVo()).addObject("stateList", stateList);
	}

	@PostMapping(value = "admin/insertCity")
	public ModelAndView insertCity(@ModelAttribute CityVo cityVo) {
		this.cityService.save(cityVo);
		return new ModelAndView("redirect:cities");
	}

	@GetMapping(value = "admin/cities")
	public ModelAndView viewCities() {
		List<CityVo> cityList = this.cityService.search();
		return new ModelAndView("admin/viewCities", "cityList", cityList);
	}

	@GetMapping(value = "admin/deleteCity")
	public ModelAndView deleteCity(@RequestParam("id") int id) {

		CityVo v1 = this.cityService.seachById(id);
		System.out.println(v1.getCityName());
		this.cityService.delete(id, v1);

		return new ModelAndView("redirect:cities");
	}

	@GetMapping(value = "admin/editCity")
	public ModelAndView editState(@RequestParam("id") int id) {

		CityVo cityVo = this.cityService.seachById(id);
		List<StateVo> stateList = this.stateService.search();
		if (cityVo != null)
			return new ModelAndView("admin/addCity", "CityVo", cityVo).addObject("stateList", stateList);
		return new ModelAndView("redirect:cities");
	}

	@GetMapping(value = "api/cities")
	@ResponseBody
	public ResponseEntity<List<CityVo>> apiCities(@RequestParam("state") int stateId) {
		List<CityVo> cityList = this.cityService.getCitiesByStateID(stateId);
		return ResponseEntity.ok(cityList);

	}
	
	@GetMapping(value = "api/state")
	@ResponseBody
	public ResponseEntity<String> apiState(@RequestParam("city") int cityId) {
		CityVo cityvo = this.cityService.getStateByCityID(cityId);
		StateVo state = cityvo.getStatevo();
		return ResponseEntity.ok(state.getStateName());

	}
}
